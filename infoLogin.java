package com.unsplash.pickerandroid.example.info;

public final class infoLogin {
	private String _txt="Invalid User/License, please contact info@ezmovil.net";
    private static String _loginUser;
    private static String _loginPass;
    private static String _image;

    //sets
    public void setImage(String image) {
        _image = image;
    }

    public void setLoginUser(String loginUser) {
        _loginUser = loginUser;
    }

    public void setLoginPass(String loginPass) {
        _loginPass = loginPass;
    }

    //gets
    public String getImage()
    {
        return _image;
    }

    public String getLoginUser()
    {
        return _loginUser;
    }

    public String getLoginPass()
    {
        return _loginPass;
    }

}
