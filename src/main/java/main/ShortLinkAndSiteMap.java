package main;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import model.SiteMap;
import model.list.ListCj;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShortLinkAndSiteMap {
    public static void createShaorAndSite(){
        Statement stmt = getStatement();
        try {
            ResultSet rs = stmt.executeQuery("select pm.pid,c.category_name as cat3,c2.category_name as cat1 from product_mapping pm join category3 c on pm.category_id = c.id join category2 c22 on c.category2_id = c22.id\n" +
                    "join category1 c2 on c22.category1_id = c2.id where pm.id in (2982,2983,2984,2985,2986,2987,2988);");
            List<SiteMap> siteMaps = new ArrayList<>();
            while (rs.next()) {
                SiteMap siteMap = new SiteMap();
                siteMap.setPid(rs.getString("pid"));
                siteMap.setCategory1(rs.getString("cat1"));
                siteMap.setCategory3(rs.getString("cat3"));
                siteMaps.add(siteMap);
            }
            File file = new File("C:\\Users\\ali\\Documents\\short-sitemap\\short.txt");
            File file2 = new File("C:\\Users\\ali\\Documents\\short-sitemap\\sitemap.txt");
            int counter=878;
            for (SiteMap s : siteMaps) {
                CharSink chs = Files.asCharSink(
                        file, Charsets.UTF_8, FileWriteMode.APPEND);
                CharSink chs2 = Files.asCharSink(
                        file2, Charsets.UTF_8, FileWriteMode.APPEND);

                    chs.write("\n" +
                            "    location /p/wojow" + counter +"{\n" +
                            "        return 301 https://wojow.shop/categories/"+s.getCategory1().replace(" & "," and ").replace(" ","-")+"/"+s.getCategory3().replace(" & "," and ").replace(" ","-")+"/"+s.getPid()+";\n" +
                            "    }");
                chs2.write("<url>\n" +
                        "<loc>https://wojow.shop/categories/"+s.getCategory1().replace(" & "," and ").replace(" ","-")+"/"+s.getCategory3().replace(" & "," and ").replace(" ","-")+"/"+s.getPid()+"</loc>" +"\n" +
                        "<lastmod>2025-03-24T18:49:05+00:00</lastmod>\n" +
                        "<priority>0.80</priority>\n" +
                        "</url>\n");

                String sql="update offer_product set short_url ='/p/wojow"+counter+"' where pid='"+s.getPid()+ "';";
                stmt.executeUpdate(sql);
                System.out.println("updated");
                counter++;
            }
            }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static Statement getStatement () {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://159.69.242.158:5432/postgres",
                            "postgres", "ali680313");
            //c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }
}
