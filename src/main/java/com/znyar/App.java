package com.znyar;

import com.znyar.display.Menu;
import com.znyar.display.authentication.ConsoleAuthenticationMenu;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Menu menu = new ConsoleAuthenticationMenu();
        menu.start();
    }

}