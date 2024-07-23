package com.kth.project_dollarstore.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kth.project_dollarstore.model.Customer;
import com.kth.project_dollarstore.model.ReceiptMetaData;
import com.kth.project_dollarstore.service.CustomerService;
import com.kth.project_dollarstore.service.ReceiptService;

@SpringBootTest
@AutoConfigureMockMvc
public class ReceiptControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ReceiptService receiptService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void shouldUploadReceipt() throws Exception {
        Customer customer = new Customer();
        customer.setName("Joar Gerlov");
        customer.setEmail("gerlov@kth.se");
        customer.setPassword("password");

        String result = customerService.addCustomer(customer);
        assert result.equals("Customer registered successfully");

        Optional<Customer> savedCustomer = customerService.getCustomerByEmail("gerlov@kth.se");

        MockMultipartFile file = new MockMultipartFile(
                "file", "receipt.jpg", MediaType.IMAGE_JPEG_VALUE, "test receipt content".getBytes());
        String butik = "110";
        String datum = "2024-07-10";
        String tid = "13:01";
        String kvittonummer = "123456";
        Float totalPrice = 100f;

        mockMvc.perform(multipart("/api/v1/customers/" + savedCustomer.get().getId() + "/upload")
                .file(file)
                .param("butik", butik)
                .param("datum", datum)
                .param("tid", tid)
                .param("kvittonummer", kvittonummer)
                .param("total", String.valueOf(totalPrice)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.butik").value(butik))
                .andExpect(jsonPath("$.datum").value(datum))
                .andExpect(jsonPath("$.tid").value(tid))
                .andExpect(jsonPath("$.kvittonummer").value(kvittonummer))
                .andExpect(jsonPath("$.totalPrice").value(totalPrice));
    }

    @Test
    public void shouldGetCustomerReceipts() throws Exception {
        Customer customer = new Customer();
        customer.setName("Joar Gerlov");
        customer.setEmail("gerlov@kth.se");
        customer.setPassword("password");

        String result = customerService.addCustomer(customer);
        assert result.equals("Customer registered successfully");

        Optional<Customer> savedCustomer = customerService.getCustomerByEmail("gerlov@kth.se");
        Date receiptDate = dateFormat.parse("2024-07-10");

        receiptService.saveReceipt(
                new MockMultipartFile("file", "receipt.jpg", MediaType.IMAGE_JPEG_VALUE, "contentstf".getBytes()),
                "110",
                receiptDate,
                "13:20",
                "12345689",
                1200.99f,
                savedCustomer.get().getId());

        mockMvc.perform(get("/api/v1/customers/" + savedCustomer.get().getId() + "/receipts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].butik").value("110"));
    }

    @Test
    public void shouldGetReceiptImage() throws Exception {
        Customer customer = new Customer();
        customer.setName("Joar Gerlov");
        customer.setEmail("gerlov@kth.se");
        customer.setPassword("password");

        String result = customerService.addCustomer(customer);
        assert result.equals("Customer registered successfully");

        Optional<Customer> savedCustomer = customerService.getCustomerByEmail("gerlov@kth.se");
        Date receiptDate = dateFormat.parse("2024-07-10");

        ReceiptMetaData savedReceipt = receiptService.saveReceipt(
                new MockMultipartFile("file", "receipt.jpg", MediaType.IMAGE_JPEG_VALUE, "content stuff".getBytes()),
                "110",
                receiptDate,
                "13:01",
                "123456",
                100f,
                savedCustomer.get().getId());

        mockMvc.perform(get("/api/v1/customers/" + savedCustomer.get().getId() + "/receipts/image/" + savedReceipt.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG));
    }

    @Test
    public void shouldDeleteReceipt() throws Exception {
        Customer customer = new Customer();
        customer.setName("Joar Gerlov");
        customer.setEmail("gerlov@kth.se");
        customer.setPassword("password");

        String result = customerService.addCustomer(customer);
        assert result.equals("Customer registered successfully");

        Optional<Customer> savedCustomer = customerService.getCustomerByEmail("gerlov@kth.se");
        Date receiptDate = dateFormat.parse("2024-07-10");

        ReceiptMetaData savedReceipt = receiptService.saveReceipt(
                new MockMultipartFile("file", "receipt.jpg", MediaType.IMAGE_JPEG_VALUE, "content stuff".getBytes()),
                "110",
                receiptDate,
                "13:00",
                "1234567",
                100f,
                savedCustomer.get().getId());

        mockMvc.perform(delete("/api/v1/customers/" + savedCustomer.get().getId() + "/receipts/" + savedReceipt.getId()))
                .andExpect(status().isOk());
    }
}
