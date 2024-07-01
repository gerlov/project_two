import requests
import pytesseract
from PIL import Image

# Load the image
# image_path = "backend/services/kvitto3.jpg"
# image = Image.open(image_path)

# # Use pytesseract to do OCR on the image
# text = pytesseract.image_to_string(image, lang='eng')

# print(text)

output_text_file = "backend/services/extracted_text.txt"

with open(output_text_file, 'r') as file:
    print(file.read())

# print(f"Extracted text has been saved to {output_text_file}")


# def send_data_to_backend(data):
#     url = "localhost:8080/api/v1/receipts"
#     response = requests.post(url, json=data)
#     return response.status_code, response.json()

# parsed_data = {
#     "butik": "115 K03",
#     "datum": "111104",
#     "tid": "13:40",
#     "nr": "5279",
#     "items": [
#         {"name": "MULTIOPPNARE", "price": 30.00},
#         {"name": "PALMOLIVE 500 ML", "price": 30.00},
#         # Add all items here
#     ],
#     "total_price": 391.00
# }

# status_code, response_data = send_data_to_backend(parsed_data)
# print(status_code, response_data)
