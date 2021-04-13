package com.unsplash.pickerandroid.example.info;

public final class infoPhotos {
	private String _txt="Invalid User/License, please contact info@ezmovil.net";
    private static String _loginUser;
    private static String _loginPass;

    //sets
    public void setLoginUser(String loginUser) {
        _loginUser = loginUser;
    }

    public void setLoginPass(String loginPass) {
        _loginPass = loginPass;
    }

    //gets
    public String getLoginUser()
    {
        return _loginUser;
    }

    public String getLoginPass()
    {
        return _loginPass;
    }

}
