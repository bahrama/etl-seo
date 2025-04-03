package main;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import model.OfferProductVariant;
import model.product.Product;
import model.product.Variant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
public class App {
    public static void main(String[] args) {
       // OfferProduct.offerProductImport();
       // ProductMapping.importProductMapping("2503261022041605200.html".replace(".html",""));
        ShortLinkAndSiteMap.createShaorAndSite();
    }
}
