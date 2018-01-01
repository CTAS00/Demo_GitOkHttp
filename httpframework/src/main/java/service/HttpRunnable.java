package service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import ctas.httpframework.http.http.HttpReponse;
import ctas.httpframework.http.http.HttpRequest;

/**
 * Created by CTAS on 2017/12/30.
 */
public class HttpRunnable implements Runnable {
    private HttpRequest httpRequest;
    private MoocRequest moocRequest;

    private WorkStation workStation;

    public HttpRunnable(HttpRequest httpRequest, MoocRequest moocRequest,WorkStation workStation) {
        this.httpRequest = httpRequest;
        this.moocRequest = moocRequest;
        this.workStation = workStation;
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public MoocRequest getMoocRequest() {
        return moocRequest;
    }

    public void setMoocRequest(MoocRequest moocRequest) {
        this.moocRequest = moocRequest;
    }

    @Override
    public void run() {
        try {
            httpRequest.getBody().write(moocRequest.getData());
            HttpReponse reponse = httpRequest.execute();
            if(reponse.getCode().isSuccess()){
                moocRequest.getResponse().success(moocRequest,new String(getData(reponse)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            workStation.finish(moocRequest);
        }


    }
    public byte[] getData(HttpReponse reponse){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream((int) reponse.getContentLength());
        int len;
        byte[] data = new byte[512];
        try {
            while((len = reponse.getBody().read(data))!=-1){
                outputStream.write(data,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();

    }
}
