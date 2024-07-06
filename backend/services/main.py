from fastapi import FastAPI, File, UploadFile, HTTPException
from pydantic import BaseModel
import pytesseract
from PIL import Image
import re
import traceback

app = FastAPI()

class Item(BaseModel):
    name: str
    quantity: int
    price: float

class ParsedReceipt(BaseModel):
    butik: str
    date: str
    time: str
    kvitto: str
    total: float
    items: list[Item]

def extract_receipt_info(receipt_text):
    # Extract butik number
    butik_match = re.search(r'(\d{3})ks\d{2}', receipt_text)
    butik = butik_match.group(1) if butik_match else "Unknown"

    # Extract date
    date_match = re.search(r'(\d{4}-\d{2}-\d{2})', receipt_text)
    date = date_match.group(1) if date_match else "Unknown"

    # Extract time
    time_match = re.search(r'(\d{2}:\d{2})', receipt_text)
    time = time_match.group(1) if time_match else "Unknown"

    # Extract items
    items = []
    item_pattern = re.compile(r'([A-Za-zåäöÅÄÖ\s]+)\s+\d+\s+St\.\s*\(à\s*(\d{2},\d{2})\)')
    for match in item_pattern.finditer(receipt_text):
        item_name = match.group(1).strip()
        item_price = match.group(2).replace(',', '.')
        items.append({
            'name': item_name,
            'quantity': 1,  # assuming quantity is 1 for simplicity
            'price': float(item_price)
        })

    # Extract total price
    total_match = re.search(r'Totalt\s+(\d{1,3}(,\d{3})*(\.\d{2})?)', receipt_text)
    total = total_match.group(1).replace(',', '.') if total_match else "0.0"

    return {
        'butik': butik,
        'date': date,
        'time': time,
        'total': float(total),
        'items': items
    }

@app.post("/parse-receipt/", response_model=ParsedReceipt)
async def parse_receipt_api(file: UploadFile = File(...)):
    try:
        # Save the uploaded file
        image = Image.open(file.file)
        # Perform OCR
        receipt_text = pytesseract.image_to_string(image)
        print("OCR Text:", receipt_text)  # Add logging to see the OCR text
        # Parse the receipt
        parsed_data = parse_receipt(receipt_text)
        return parsed_data
    except Exception as e:
        print("Error in API endpoint:", e)
        traceback.print_exc()
        raise HTTPException(status_code=500, detail=str(e))
