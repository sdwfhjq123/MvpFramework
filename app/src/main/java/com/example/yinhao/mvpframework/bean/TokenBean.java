package com.example.yinhao.mvpframework.bean;

public class TokenBean {

    /**
     * access_token : eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MjE1MjczODYsInN1YiI6IntcImxvZ2lubmFtZVwiOlwiYWRtaW4xXCJ9In0.80Cw0mvqxW1ugGPfEReb1UD_VZOChokUYm9OTAnumug
     * expires_in : 60000
     * refresh_token : eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MjE1MjczODYsInN1YiI6IntcImxvZ2lubmFtZVwiOlwiYWRtaW4xXCJ9In0.2ifkNo-CjpSelWWj4Yw2E2DyebgOedTgWqp1RaZM2Kc
     * token_type : bearer
     * binding : null
     * register : null
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String token_type;
    private Object binding;
    private Object register;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public Object getBinding() {
        return binding;
    }

    public void setBinding(Object binding) {
        this.binding = binding;
    }

    public Object getRegister() {
        return register;
    }

    public void setRegister(Object register) {
        this.register = register;
    }
}
