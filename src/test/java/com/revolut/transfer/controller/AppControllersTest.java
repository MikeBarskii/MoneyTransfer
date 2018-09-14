package com.revolut.transfer.controller;

import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpResponse;
import com.despegar.http.client.PostMethod;
import com.despegar.sparkjava.test.SparkServer;
import com.revolut.transfer.model.Transfer;
import com.revolut.transfer.service.RestService;
import com.revolut.transfer.service.impl.BaseRestService;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Test;
import spark.servlet.SparkApplication;

import java.math.BigDecimal;

import static com.revolut.transfer.util.JsonUtilInTest.convertToObject;
import static com.revolut.transfer.util.JsonUtilInTest.toJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AppControllersTest {
    private static RestService restService;

    public static class ControllerTestSparkApplication implements SparkApplication {
        @Override
        public void init() {
            restService = new BaseRestService();
            restService.start();
        }
    }

    @ClassRule
    public static SparkServer<ControllerTestSparkApplication> testServer =
            new SparkServer<>(AppControllersTest.ControllerTestSparkApplication.class, 4567);


    @Test
    public void testGetTransfers() throws Exception {
        GetMethod get = testServer.get("/transfers/", false);
        HttpResponse httpResponse = testServer.execute(get);
        assertEquals(200, httpResponse.code());

        assertNotNull(testServer.getApplication());
    }

    @Test
    public void testCreateTransfer() throws Exception {
        Transfer transfer = new Transfer(1, 3, BigDecimal.valueOf(100));
        PostMethod post = testServer.post("/transfers/create", toJson(transfer), false);
        HttpResponse httpResponse = testServer.execute(post);

        Transfer transferFromServer = convertToObject(httpResponse.body(), Transfer.class);
        assertEquals(200, httpResponse.code());
        assertEquals(transfer.getSenderId(), transferFromServer.getSenderId());
        assertEquals(transfer.getReceiverId(), transferFromServer.getReceiverId());
        assertEquals(transfer.getAmount(), transferFromServer.getAmount());
        assertNotNull(testServer.getApplication());
    }

    @Test
    public void testCreateTransferAndGetById() throws Exception {
        Transfer transfer = new Transfer(1, 2, BigDecimal.valueOf(150));
        PostMethod post = testServer.post("/transfers/create", toJson(transfer), false);
        HttpResponse httpResponseFromCreate = testServer.execute(post);
        assertEquals(200, httpResponseFromCreate.code());

        Transfer transferAfterCreate = convertToObject(httpResponseFromCreate.body(), Transfer.class);
        String pathToTransferById = "/transfers/" + transferAfterCreate.getId();
        GetMethod findCreatedTransferById = testServer.get(pathToTransferById, false);
        HttpResponse httpResponseGetTransferById = testServer.execute(findCreatedTransferById);
        assertEquals(200, httpResponseFromCreate.code());

        Transfer transferInDb = convertToObject(httpResponseGetTransferById.body(), Transfer.class);
        assertEquals(transfer.getSenderId(), transferInDb.getSenderId());
        assertEquals(transfer.getReceiverId(), transferInDb.getReceiverId());
        assertEquals(transfer.getAmount(), transferInDb.getAmount());
        assertNotNull(testServer.getApplication());
    }

    @Test
    public void testCreateTransferWithNotEnoughBalance() throws Exception {
        Transfer transfer = new Transfer(1, 3, BigDecimal.valueOf(9999999999L));
        PostMethod post = testServer.post("/transfers/create", toJson(transfer), false);
        HttpResponse httpResponse = testServer.execute(post);

        assertEquals(HttpStatus.BAD_REQUEST_400, httpResponse.code());
        assertEquals("Account with id: " + transfer.getSenderId() + " doesn't have enough money: " + BigDecimal.valueOf(9999999999L),
                new String(httpResponse.body()));
        assertNotNull(testServer.getApplication());
    }

    @Test
    public void testCreateTransferToTheSameAccount() throws Exception {
        Transfer transfer = new Transfer(1, 1, BigDecimal.valueOf(100L));
        PostMethod post = testServer.post("/transfers/create", toJson(transfer), false);
        HttpResponse httpResponse = testServer.execute(post);

        assertEquals(HttpStatus.BAD_REQUEST_400, httpResponse.code());
        assertEquals("Can't send money to the same account!", new String(httpResponse.body()));
        assertNotNull(testServer.getApplication());
    }

    @Test()
    public void testGetDefaultCustomers() throws Exception {
        GetMethod get = testServer.get("/customers/", false);
        HttpResponse httpResponse = testServer.execute(get);

        assertEquals(HttpStatus.OK_200, httpResponse.code());
        assertNotNull(httpResponse.body());
        assertNotNull(testServer.getApplication());
    }

    @Test()
    public void testGetDefaultAccounts() throws Exception {
        GetMethod get = testServer.get("/accounts/", false);
        HttpResponse httpResponse = testServer.execute(get);

        assertEquals(HttpStatus.OK_200, httpResponse.code());
        assertNotNull(httpResponse.body());
        assertNotNull(testServer.getApplication());
    }

    @AfterClass
    public static void closeTestApplication() {
        restService.stop();
    }
}