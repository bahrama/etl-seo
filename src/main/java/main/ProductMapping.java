package main;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import model.list.CjProductList;
import model.list.ListCj;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductMapping {

    public static void importProductMapping(String pidInp){
        String token = "API@CJ3913573@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMTcxNCIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnloVFdFN1p4Ni9zMFF3SUVyQ0hpRyszdHNYK3VnSXJKVXhvMi91VStxeW1oaGR6TFFVa3JQem9MUWVGZlRSZ2ZOcjdEV20vUTAvOWc0NGFhWjdldkNGTU5aUHNrODF4TVlaRm9LTG9GblF5WGVJVmdyK2xnWC9Ma0U5U2xUd2NySTQ2SFQ4Q3dueWdrYUM0eUlNNE5JT3hnRzR3SnJweGpONzRSY0pkaEd5NFJJQ3QxR2ZybURiY0lWSmRlNzNxK1luT1loQ1ZlZEJUenZVK2YzWlpwK0dUTEpOWmdjK1h1VFUrR29LMXp5MGRwRVVwRGFxMEZveEtCMzZXamZNWmNTOHZmaFpLWGlqRHNYbTBreTZuSHNSdz0iLCJpYXQiOjE3NDA1MTQwNDR9.MlgRAobwrG_UA7voWVPaCP1sDDW3Rcwpo6Lxl-EcTRg";
        String token2 = "API@CJ3913891@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMTcxNSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnl2QkU4d1JuNGprYXNJVWVhcjhzdHZCM3o1MGczUTZsb1NoU25ST0pLam9waGR6TFFVa3JQem9MUWVGZlRSZ2ZOa0Vnd1lZdlh0RGRnVG1Qa3ByNmlKa05aUHNrODF4TVlaRm9LTG9GblF5WEEvaHErRUlNbUhwbGlycnVMRWdhRk1DYXlQVEU0dG9vRDRUUUEwYUVuTm4xMmU5TFBaeXViRFlCZm1LNTYveVJ0cHkzRVNmN254YW1hRjhEY1FGSkVnWURNWXFzRm9sWElnMEdtQm1CbUNHMktZSFJGeG5aamlWd2pIaTd5Yk5HNlhrMW9FMSs0RVdYOW56WVpBSTRxTGhSYmhZdTl0bitSRWVGQXNwMEYyUT0iLCJpYXQiOjE3NDA1MTQwODF9.6cpSt8z9CPrKM8cQowCoSIopGWHuod9qUtA05gN7EfA";
        String token3 = "API@CJ3914046@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMTcxOCIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlvV1E2SkFTRWVHSXhWU3FWZHIxLzRKM3o1MGczUTZsb1NoU25ST0pLam9waGR6TFFVa3JQem9MUWVGZlRSZ2ZObGxRRUhaSytoZzlEY3NiOHlWaFZEZ05aUHNrODF4TVlaRm9LTG9GblF5WEtyM2x3S295cndQajZZbGZBTWhsYnN4WUFYMmVlTXh3ai9ySzNua1NVQy9yTW5PT2JyOC91OUx3eVQwOXpwZ3JDTXNzb3Bkb25TekhYRTRlb1VpZG1VWWh4cnpXK2FBUU44enVsVGk3Nk5qQUVyRGIrNlE1YlplLzEvUHBWNDd2SUE2MllEN0tmZGZsdUZHOXJubDdSbmxrMHN6cjdhMVZDZ2ZzSGZRenY0MD0iLCJpYXQiOjE3NDA1MTQxMTF9.CraSmFdWuPrdroMlu8-AnVPnazF8XZKzDap-88Of5ew";
        String token4 = "API@CJ3914565@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMTcyMiIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlsZHAwYzBtSDkwY1Z0NlRYMzhNRVJRLzVIcXl0M0ZXOWlOMmtFY0U4SUtvN1pQUEJ6Y1RYWFhEZzNyOHdqYWxvNVJRcVhpUyt3UEd4ZW5pMmljV0xmZHU4VTU4Tkw4dXRCaEZSd25iM1pqRjdwNmNKOWtmY2YzNzdCUWYycjlXY2FZR0dHUjc1TWt4eW1kbFpvNVhBTzJQbVFQTWJaN1E1aWpEMFZGVWhzaW9mcXZiaXBqMWtOVm1vUERqYWRQcEhJL2JOd2lnMTFXVi9DaVdGVllkWUQ3WmtZcURKWFQyQ1BRK2V3S0o2ZVYvMEgydk9ZQTI2Q2dNbzl0Mkk2NUMvN0NjT0pyRjZlaU9adm9ldXNwUjJtbz0iLCJpYXQiOjE3MzgxNzI2MTR9.1UkLxzBxdG6FcjH2Eie_lLyWIZWESB0gMjlwr-6RM3s";
        String token5 = "API@CJ3914579@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMTcyMyIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlsbi9GZXVrT2gxSERBVHNPdktMN1RuRTZsWUdPZUtuWUVsaFErQUZPN3NSdlA1c29aekpPajVuM3ZRNEg5dllOcGhVOWpMS0ZCNFlVUGRkUEdZYkx3S1VHWTRpYnJwR25EbWtXYmlpRWZYRmVYWW1ncmh5QlFJN2V4dEs3VzBQclkvZGZCeTZDQ0hGK08rbGZkdjE4RWFqNy9BWk1GYkdRb3pycnVEMmc1K0NKRXM0TUFmSnNnaXVIS2tCZFJWZ25rWWh4cnpXK2FBUU44enVsVGk3Nk5nTmxwTk1QWTBIUHlLbFIvNXY3cVpDa0J0cjBBL2NWWUtUSWFtMXhaUmhaUWtScXlZWW9WTCszOUZ6S3dYRTlCVT0iLCJpYXQiOjE3MzgxNzI2NDR9.iE7tyALbp1G2tURibWzP-VE_JQTDYf6yjNM8vgJIAks";
        String token6 = "API@CJ3914589@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMTcyNCIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnluaUpnK3R1T25zbkwyYmxXTGdPSTlnZDIra05YMFF6NVhFQnU3RWJXQWdPNU5YRUszWjA4KzFWanBoSGI5cUNuNHBUZE1xY2tlTHVwdEpEVzJ1RjNYNkEwbGsxc1Q5SGpiajVhRlZhR1psRUlSQ0w3YVFnUTh6WUkwVVN2bHBsVkQ4TC9xTCtObUEyMkNFZXl4WFpkNy9HZkJxTENFSkRTWFJvT0VmSDh3TjljVkNTS3pNWWZwNGFHRnpZN0FBY2JldktqT2Z4VkdYZzBxTWZNSkkyay80VksrbjdRM0Z0SVRYdGFYRVZBZFUxa0ZCdHRrTWVLaW0vQkhFcEY4b0wvekZyMEkxRWozUUUwNEFZRUZJM1dRaz0iLCJpYXQiOjE3MzgxNzI2ODJ9.2yNfoK1YHF-c6dBaY5iFYRchngLDvlBwt4lwekf9TDw";
        String token7 = "API@CJ3914589@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMTcyNCIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnluaUpnK3R1T25zbkwyYmxXTGdPSTlnZDIra05YMFF6NVhFQnU3RWJXQWdPNU5YRUszWjA4KzFWanBoSGI5cUNuK1BBN2hHMnYxTG4yMnVhKy9hM00xS0EwbGsxc1Q5SGpiajVhRlZhR1psRUlSQ0w3YVFnUTh6WUkwVVN2bHBsVkQ4TC9xTCtObUEyMkNFZXl4WFpkNy9HZkJxTENFSkRTWFJvT0VmSDh3TjljVkNTS3pNWWZwNGFHRnpZN0FBY2JldktqT2Z4VkdYZzBxTWZNSkkyay80VksrbjdRM0Z0SVRYdGFYRVZBZFUxa0ZCdHRrTWVLaW0vQkhFcEY4b0wvekZyMEkxRWozUUUwNEFZRUZJM1dRaz0iLCJpYXQiOjE3NDA0Mjc2MTN9.qNXo1G5oxOcE17V4Ne_spBkdHE_687cbx-jEG2ZDNHU";

        String token8 = "API@CJ3914605@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMTcyNSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnl0eEc2dGJGd09mSFYrc3AxRlhzMU54SUYxUmxGNWVaZ2U1T2szSWo4ODdwL3g5b3ZnTTdzRnp2M3dyeHlTRVg3NTc3RVRxR25BbFF6YlBsUURaN2daT1U2MzIwRW1GM2ZXR0svMHRqNnhqZUN3UHFqelVQMGkxMnhVMlk1N2JvaWpoTDREMHdnWDVmL3dYNzdRM01HeXZZZ2d5aStBVERFUUV0UE5qZm9icHJOZVVRc3Vjc1FobnRreGhmdFVvc1ZZbVQ2aHhiL1BuU2o1MG1TSWFkMkVlL2pMcVFGeUNJTUlwOGtHQ05WQi9oN1M4Y1p1aVpRWkp6anJBanlCTzFHT0U3VzFSd2FLQ1dHemxhbWlnOEJtcz0iLCJpYXQiOjE3NDA0Mjc2NTR9.BFGO-IlwjyZzYh3TANoZN4Y0vwdrWRGxLrJPy5iYrGg";
        String token9 = "API@CJ3983152@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE3OSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnl2b3VLajV2cUNOYzZnakpUMXN5cWpiaW5BTjJsK2ZXS3M5anFqM0RIVUtVcE42QmRybUI3VWNuaXRaZkZrNHNuOVlsMWlqSjBqMFdJN25Ud0h2QXNHNFVHVW15WFZJSy83UnFPUnNGT01OYkRNajNBd2JDdWNnUnhWaUM5dnErQlJJdzgvRDd2cWtDTzdLeXdZa20zTHh3d21LMjBRSTZYQjd3ejlZaW5Jd0cxT3dFM0U4Nzd4dmFsc3dpMGEzOTVVYTZQWWpFZnNVWnBScVZNMEpGR05lK2RZSy9UQlRKSFpWRFVTN0dnUEs3aWNmVHkrdkgzbW11VG1iT1RYMVJJV2txZVB2eVFFb3BkWXlQR1RyVVNnWGVUQXZEeXNpRGFSdEdoT2tLaldiNiIsImlhdCI6MTc0MDQyNzY4Mn0.MrUeKWKEGpWvnJYYSZ9V9WOYG_kIHGbUN7kAFBxcz3M";
        String token10 = "API@CJ3983169@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE3OCIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlpcDArZkFnWlc1YUp3a1FZemE1M1JSZjVmbUpGZXhicXlDaEQ5QXRlMSs1NU5YRUszWjA4KzFWanBoSGI5cUNuNjNTTjN1Nk1aV2YwZXR3WEJiS1d6dUEwbGsxc1Q5SGpiajVhRlZhR1psRTZJcVdmdmE0a21BVHFWYkNWRDVmZGJMdHVoSXpNK2ZXS2hUTGVkQzh2ZUhUYkdHeDFPWnh4Y1NscWhpL1RzV0VRTlJPMUJrQXgvWW5mWEMzbEpYS0YyWkRISFRkNEwxd1RsMmRadlh5WTl3U0Qzc1NyTjR0UkVkWlVxaEtLQm5EdkxkUjM1eFVPUE56K1E5a0FHNjdRdllCdHUybThOc0NSSWFWTU9makdQTUNUc0d0RFpIaENMUkk2Y3lJeFNGUyIsImlhdCI6MTc0MDQzMTA4OX0._BLEdI1xvf8OL86LZL4rW7IvRZXLZxqCl9R_qXhU1b0";
        String token11 = "API@CJ3983176@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE4MCIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnloZkpiM1FCSDF6T01IcUdoSjlYemliRVViV3VNN0h2NU9WTzVkQVdVTS9lNElQNDlwUTFieW5qUEVaTzFZUGk3aHZuQVA2Q05QaU41TUY4NUhtOVFjZkdSc2xidTNjc0RZOWtZNitpc2FIcFJWRDNRdzN1NWd0TExVdFpUQzdsRkQ5QUtNNFhGRXVqNzk0U1FCUWhOckhhQXNSRWF1Yk82Q3JOdVNpY0JwNWhhYmUzcWwxKzJwVGUyWS9zTFhWUXZHQkllc2x4Qy9rYlZHZEE3c2J0TFE4dXk1ZEhDeHFKS1JmeUd0U0lDdmdtMTd6YUxqY09uZFlmWDFLZ09vSHFGU1AzUFAwOGRBVjFjalkwUWtpWVFjR1ZCUUVyM0JqWWN5dHJkcmZtTE5nOGVjc0dtN3Fvc005Q0haRklnT1hFaXc9PSIsImlhdCI6MTc0MDQzMTEzMH0.sYdTDsbMthjZxAl5wMMQMW5_5bO4H4rcRTh0m8VBxTs";
        String token12 = "API@CJ3983231@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE4MSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnl2K2xzOHNZeTcyZDBSZDVjbjZUVTRTRnhTNnNuaVVjbmt4VlUvNDFtQXFaNU5YRUszWjA4KzFWanBoSGI5cUNuK2UzRFJvOFZZeVNNWWdLeE9jcHYwYUEwbGsxc1Q5SGpiajVhRlZhR1psRWdjQUJlMlVhQjUwM3pid09USCtqQWlOYmVzdW9RaHpHU1hqWURIMlhoR1pnakYzYktBVG1ONUx3Y3JkQU5NcVFxTXBuZncybis4bWlYbE4xZFJ0eEdvL2JOd2lnMTFXVi9DaVdGVllkWUQ3QWc0cFhaRTAzd1k0M0NZYmxwTmQzckc3aEVzaWR1Z0NIdVFTU0d0ckhjNmpLWjM4TnAvdkpvbDVUZFhVYmNScmRDbCtER3RmNjNXSUFTOHVrK2M2eiIsImlhdCI6MTc0MDQzMTE1OX0.n6bC6HV8_F2nU-QOoWQ-zcGl4gBhWvrLbgHQjZnaKWc";
        String token13 = "API@CJ3983245@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE4MiIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlpelBMY2NteGFVOVBpbkFMUXlIc092MXdSOW9tajY5eHhaZ0M4SE9iazhqUFh0T2VRWEdLaXF3ZFVDdy9PTGtVUE4xdlVEMVVwZkd3M1Q5ajlYSVJ3UWtFemZmR3Z2MW9TdExQakNxNkE1MnVLbXRKd2hhblVTY2FOSXYvUHZHL3cyLzJIYXNsWHg2emErYVNMbitqWi96eDBEN2lJRjE2NWMzKzRqdDNEaldQUDZLVHA0OFpKaE16TWV6dllpbVpvL2JOd2lnMTFXVi9DaVdGVllkWUQ2VmQvT2FuV2xXaUtqUE54aFIvSTRtTmhCZUZiYzhRY0dzVkY4Tno0aURXMHZKZS8ydHRaN0s5bDBldzRYQXliVzQ1YjFNRUlHbTFucjdrQjdaTXVKdiIsImlhdCI6MTczODMxMzA3Nn0.N2LGfGbCWsjdgdm5Wp4694jkH2M0a3MXPBuCf2olykw";
        String token14 = "API@CJ3983287@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE4NSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnl2WEtHZTk2QlU5K3V6K2lKYklKTnhLS0UyOEZrMkRjZCtTMzg0MEdVYXFSYTVkaFVZMUFoZWZJTWZzVW42dHhPTXZXRGRkZUlaLzFoU3dubVlMNUFZdXN4SUE5QjFUb2tDZE9YdXMvVVJ1SWYySkdWcCtjL2ZTQzNOWjV6M0J3cnB0UGpDSG9xTE8rVWszaG9xTFI5ckJkVHg1bXZNNVF6ajByTFNYc0ZUcFVNVWFjbUhoMUxOam94a0RyUHFKSUpuNGw2aGduRDVodTI4cFI1cklWaHllbnJPbDlkRFhWUGJYOW5nMXRHMWpVZXZrNG5UUEphL3JFZjVrTGFmWWgzYW1DeDhhY3FGOHVmdU50S2lJRmhzTlg5NHFYOTJIUXNTVFNydjRDMjhjWCIsImlhdCI6MTczODMxNDE5M30.8FgLu1R1nj-mcuo6_N_L1_j-uaSenauvaGxppuf8LAg";
        String token15 = "API@CJ3983292@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE4NiIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlqdUlsNGtZUTB3TzFIdlQ3cjRaQktoOTN5WG9tZ25OL2RiZVJ5OGtCVDV5cE42QmRybUI3VWNuaXRaZkZrNHNuem9kaEF1STNyeS9yWk9pcUU5WVBlcmNiS21oZ0VtaVZhYTViNFhNdEt5OE1Kc01Cd2xGaXYyRXFrV1lhZXk1QnQ1alNRNHovRVVqL1U5WmlrMlVlS25LSXJjUmVVRmVBanZJM2RTcW1nQ3FMN0lKdEdPZE1hVjhJMVlwaVNGbXdOUVlxdzMyYkZrNU95ejhieWMwNUxHbmEraSsrYjlTUDJXN0Zva1V6N3h3OUl1NzF3Tlo3MXdpWXRpc1dUQ1djeHNGTGtraFdqd3ZqWHpMTUZpQytnV2JSVFpwT01jR2J4Z011UThnS2MrKyIsImlhdCI6MTczODMxNDM0MH0.VWdGc7Zx0PEUDbE-v_571vQy-hsGhNoMLhSHO8t-eD8";
        String token16 = "API@CJ3983298@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE4NyIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlzalVXa21uRGZxRXZ1aUpxWUdiSlNyaERMZDJoK1MzYW9TTi9aL1BvN3Zxb1VOV214dHB3c3ZzalBKV3JOYlBiQjVjVlRjOWFZTkhiUGdNK01rdmxGYzBLekxQQVE4MklXaGxsWWRxUHYwNCtpQnJ5czhqWWhaNGg2VlcrUDF2MHYzNWRtL0RDcnR5N0xtUEx6U3JZV2ltaFF6WE51eUQ0c0RPNmdFcEJhQTB4Mi9MbzBHMDNZSnp0akZmMElUaDVEVXRZMVNaVjlFTXVHR09pdVpUNW1BSjB5clRhVGRRTkNZb3gvRXF5L25iZG9ZSDhocWZwc0R1TGJobkwvT2puQTVHVit6Qk1qcFJna2x0OFppTW9TTm8wMU9VVFA2VmRPQjRkaTZ3WDNWdytPOGhTMjNtODJGRTFRTE1sVXM2L2c9PSIsImlhdCI6MTczODMxNDQ3M30.KuDXE20bkTGz8dmITHwfAde_925gQ78D7lFqqSNn5xQ";
        String token17 = "API@CJ3983972@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE5MCIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlnM0wra091MkRPWi9qMGV5cFFobU5VZFR4d01zU0FETm1aanh6cTVuZjNkNU5YRUszWjA4KzFWanBoSGI5cUNuNDdPME83M1lLUFNuYUlMS2pmdXZ5U0EwbGsxc1Q5SGpiajVhRlZhR1psRStVdlBBb1hhK1dNbGpJQnpreHRGOURQTUhpakI3YmFTMEdiYW9hQ1ppNVY5Y3gyWllCcUNiazJ0VDhUU0Zqb3RBeTRUNCtFbEhrWU1ocWdaVW5mdTJYNWNHVVYvdWw2OFVVMnErbHp4em8vN1h0MUhQMVhIMFBWTXZycjFxVmx0SDEvcnI5S2IxSXJTaWQ2MWp4eTV3a2cxRHlZakt1Sk5zVGM2NHZoNVlueFl1QXJmQXJaSEc2a21Ybm84UVdKSyIsImlhdCI6MTczODMzMTUxNH0.qxgN_aGg3HmgF_DrfO4Q-cmtM65HXX8EaPoAl5WnhFY";
        String token18 = "API@CJ3983978@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE5MSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnl1RTZvMHBKYWpkZHM5WTk1OEhSN1hFS2liV29XczlOUDU0akpCSzJRT2YwUFh0T2VRWEdLaXF3ZFVDdy9PTGtVSnNFQ0xxWXNLVjZpUmpEQXp4b1RSWWtFemZmR3Z2MW9TdExQakNxNkE1MjBEMmQvWEtnaFN0S3hyQ2w0RmdtSDlRTnc0OGEyeHlUUXFSbjZHVUZOUmJYS0kyT2ltUkRMa3F6Q2NOVVZ6Q0dkemd1d0hUUTg3Y0pEOUthL1BpL2JtV09veFV6VVRyeWNabnBGUHZMQ1V3MVhmeG9kVi9SZ1FLb1l2NnByYlhUR0tkTWNsMlNWMlRtVm1LbjRPdUlQUmc1bGJwbVluQjF6c1piMitiZjE5Y21zWGdiZm02U0xISEx1M2c3ZDJocCIsImlhdCI6MTczODMzMTYyMn0.nvqofRUIpACK9Ymn30WWW15dpjLw6jC4E-Jy-54rpnc";
        String token19 = "API@CJ3983984@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE5MiIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlxbzU5U3NzazBndytkZytMRVFZOXFMZ3RlNit0REJtN2hTVElwVkRyOWxPaGR6TFFVa3JQem9MUWVGZlRSZ2ZObTFIb1ZFczFFbmVpWWk0TXA0NVM1a05aUHNrODF4TVlaRm9LTG9GblF5WG9LMXZxNSswajlCc20vRlBidlZBM1dDVnlEWlJxM1VKTUR3KzN6cGQxKzFDZWptb1pEbWdNNUVFV2NocFE4aWNqV3BQOXZzQkJOUXEwbEJMMnZKTlJOTERpQUxpS1dMSDZBajJXSjNXSXVNdlFjQ1VsK2dHWWZTZUtlWXdab1FxRytESlNJNFhQb3c4N2I2Y3lQSFZ4TFp2MHJEVndmdHNQc2plY2tUZXlwVVVNY1hzeCtaTjFWRzdDZ0xZRlp6NyIsImlhdCI6MTczODMzMTcxMX0.rlqoiUcygzgBlaNCmE59_BSt42hqQRrytUfxh35ZuUM";

        String token20 = "API@CJ3984102@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE5NSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlwSjlwWWREb1NWd0owbUMvUWllY2pYR2J4bGN0TnlDMFpBclQwdzdBV29sL3g5b3ZnTTdzRnp2M3dyeHlTRVg3L0RJc2NTMFNEeTUrMURVaEdMRndpWkpaS2VzSitRbm1FVm1TYUhIS3h6QzMxUms1OFZRUHJBcnp4MW91Vmc0cjhXL2JQeWhwSHNTa3hyTUo1R2Q4eXpaSkpqUjhvYmhDWU5iak5LSG5ESWpXWXozOGthaVBSUWlzQUJMaFcwV1dhdFlyV0s2dlFLaWN2WVdXU24wRnNkL1ZqYWFtdDM4RDArQXV4ZW5FdStKZnZsaW15UWtkcmRaYkNWNXVsalVteVJUQ0ZjQWdzczlHME1PSW5NcTB4Zk9PdFBZSmtZMkQ3MjBJRWFDaU5MZFI3b0VCZXgwMHNycmZJUzZTanBqZEE9PSIsImlhdCI6MTczODMzMzk1MX0.sZswHQBOgxV2QO5B28uRCTZrZKInzSWIsepkkpmjSZs";
        String token21 = "API@CJ3984107@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE5NiIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlwQjhOYzhRSXIveDZiUTY2YkdZQnBsY0lJeHhVbXBtdDE3Wk4razdyTVNZbFNqYzdkbTI5c0k0WVpTbGxRTGIydVRWeEN0MmRQUHRWWTZZUjIvYWdwLzdMRUh4TXFGb3B3ZmFYWWxZYmJQQWdOSlpOYkUvUjQyNCtXaFZXaG1aUkRmblBVUFF0NFI3Z0lsVGxRZ2xjMU5XbStFM042UGlPdEtrSjQ0YndaUEg5QzVSTkpuOU41Rnc4Q3g0SDQ1ZGRZV2pjNVVjQjBOeHpOdk5YaXpjbVc1aERaZWIySGhhVFBRNm5NcUVFSHN2MWFpWmwyUmZpUHU2Y3dHUzRzRU45REg5VjZvTkxOTzVLRnVWUVFUTW9OYVFVTnVHWWpJTnh2b2ZJZjk0clg1SWdoRWpCK1MzT0FNRDcvbkZqekwrUGc9PSIsImlhdCI6MTczODMzNDA3NX0.NZzFMnpJlRjzGThKANqKA742n2fd3QhcijAKXZgy1eA";
        String token22 = "API@CJ3984112@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE5OCIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlrZzUrYTVOcGgzeXl1YlJCWDRWUVovMEZMVGJJcG43VDIxR1d4N0tjL2NPcGlLL1BDTklzN1N4aHNrbkpNOHpyTUV0OHRNbDBhN1lQU2tRamtYUkF2S1RUQVBFcjBWdmFNZmlhRXJVTHMzUWRQWXpINkpJakVZbnJabGdnbUExSVdUMmwzbU9ucEtIY2c4NDJzK2pKNUY0Ny9qR2YyOFFCY1NIdFRScVNaSTVLK01FZkhTazRDNXd3SUtpRkJJeG5INXQ4VHd4ZUxXUTlMWVhUWUN4c1RCZi94dW1QcVpTYzUyYnpOS1JBaTRQa1JHbFJZb0hVL0c2aGFEMzY5aExZRVlLYlRDWWRqYW83ZGJTdkNhWGt0NE9WVmRDR29aR2RPVUpydVVXOHlwYSIsImlhdCI6MTczODMzNDE4M30.BbmBnlmMjHYOVbaEf43lLtw4bwwzKAo9L9JQP1IhcR0";

        String token23 = "API@CJ3984164@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjE5OSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnl1SkdtQXJWZWJaYVpVZ09wZWdlKzdvT29nUnF0MVJMQitLTlU5NDBHWmNtSGx4Vk56MXBnMGRzK0F6NHlTK1VWK0F0OWxzS2g5TDN1aG1XMUhveWFmVDZJR3ZLenlOaUZuaUhwVmI0L1cvUy9OQXU2RVFjVGRMV0trTG5nbnN0NUUvRDJWZG05dzRjb0k5ZHliNUhENlRIYjh1alFiVGRnbk8yTVYvUWhPSGsvODQ0MWxjSzJuVzRSRWFsanloM04vUXFtWkJDVGl6NEQrektKdzJPQ2U3K3NRUXUyZ2xVYzVGVU5OY0JtRUFYcHRQOEVSZnQrMXN4RVkrNmxlTTU2bjNtMWpHeGx5NGNOcmR0QjE1VjlYQjRYVk1jTHhaMXRRT0hwYnRKSWlNOCIsImlhdCI6MTczODMzNTAxNn0.sh6mL1gTQXEjZUvz6ZPBv9ZygcTen6D2M1NMemTAlBE";
        String token24 = "API@CJ3984181@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjIwMCIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnltS2YzVHhxNFp6MjVPN3h5TXJOREVreEhqZEpJdjVsSkZDZEV4V0oyRnpuL3g5b3ZnTTdzRnp2M3dyeHlTRVg3enZXc0VkN1d2RWZKNC9iRWhJcEJXYUxkdkZMUXlzNUUvdHlYOWJqWDV5RitRYkkzQjkxVkhpU25seVdGU0t5MXJjeTlDVjRoVnpjbm5aZ09melp0cGFHS1d1dnI4cG91b3lhdUFnMUphM2xvZHZPbUMyZDNCSmI2QnlwVW5DRlpaRXZERzYzam9Pc1ZlbFZ3WTdEZmFFTUIzRHNRemwvOGNIRjVkVzdUeUdheWpITDRFcURHNUtybWU3V2QzZWh0ZWVrbXJ6L3MyeThQVlFHWlQ0c0c2MHBZdk9xazJSMVhZbWtMV2xsMXJ6LzNRcGZneHJYK3QxaUFFdkxwUG5Pc3c9PSIsImlhdCI6MTczODMzNTM0NH0.VF-5r9R6mc5FzbQIyGmhpKTs0lxWIBqutv8HIDXiX48";
        String token25 = "API@CJ3984191@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjIwMSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlnZU5IYWhUSjRKVU9IQXN2Q3RxUjJnWS9aWDJ3bVhYQUpHaFFzSTg3MmNoUFh0T2VRWEdLaXF3ZFVDdy9PTGtVREVyNk5OQUVITmpnMUwralViUG1uRmVmUzNYVUNja2ZCNVoxc1pzaTM3ZFhoNDNVc2w0Z3RlQnZHK1NyQVJqNEswaUVBd01ZYmo4RUhMWW95V3lycjdYS0kyT2ltUkRMa3F6Q2NOVVZ6Q0dtdHBRSVRvYmZxQmJDSlNCeGJZSUJBWURNWXFzRm9sWElnMEdtQm1CbUNGTDlIWnUwaEFMVk51cDhpbFZ6c0lVaE1oTkFTT2V6REluWEVXQWRZYkVackVjQTBMTng4SmxFVVluckExczA3RGRDbCtER3RmNjNXSUFTOHVrK2M2eiIsImlhdCI6MTczODMzNTQ2M30.n26GceieIBJML45Xq_NYEAv_ZKucV8ugm4Ts_41aGLg";

        String token26 = "API@CJ3984552@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjIwNSIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlsVHZTT0Y0QUQ4S3JtbzRrWFpySTBZQ29OQ1pYYlNnanlsWDBHOTlrb0djN1pQUEJ6Y1RYWFhEZzNyOHdqYWxvMFIvbUpuS0Q2Mm55N2N6a1dHbE5NMXU4VTU4Tkw4dXRCaEZSd25iM1pqRkZZYnhpY25oMUI2VjlyZ2NiRUg4QmVCTldoek8vY0Y2dHF6bzhkcEpqWTRVM2hEcVM1V3lWNnRtdW1NWUpTMlpZM09mdEkrTlgvUnNZczJ0TWxqM3NqU013V2FVK2ExQWdCdm1Vd1o3ZnBIT1hEUUxuUU9OZUtOOTZqSFBqOUlaYXlnZE1ITVNNZ1QxVVo1TnJUVFBEYkNKeWFscGVKdDkrWS92QVM2M1I0OFpWWXhqNWN2dk1UaWJWR2ZHSDVXZlFFRk9ETFE3VlJ1Q1UrdWhxbklFeUE9PSIsImlhdCI6MTczODM0MzQ4NX0.TF96Sg39WbSmcd-QKxyHhQNKpNhGCwYpYxmalbTRXw0";
        String token27 = "API@CJ3984539@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjIwNCIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlxUlRyRC9aSzVXOWkrZHdUYkRyMUNyVjI3dXZ1SjFXYTJScERuNFlkcFVqL3g5b3ZnTTdzRnp2M3dyeHlTRVg3NHVpZWVkSGd2YzU4clhtTy9DOHNGYi8xZ0VCcER3aWxERTJvdGtJdEYxVlRQS3dzS0ZESG5NQm44bkI1SC9wdnRZNk9mV2p6YXdrRDRkbjdITGw1ckdwaldxZE5CQTh3bUdERWxWK0JUb0QzQ0FUZk44eUxrSjJFMENxNmVYcU52cnpqckc1d0pDWnJMMlNnM2FqTTQ3WEszendDOXVXNVoxN2xzZ0pRV3dScmhMTTE5TEpVcEpkUjNuNm5CVmFZUEpVRVpxeXQ1bUNwSVlOcThkUlQ1ZkdtbHo4RkJRY0tkdkRlK3R0THBkQSIsImlhdCI6MTczODM0MjkyOX0.tO0X7VseHU1JZFyhqfawgTPX50yOCt0P58B1OLYBQ2g";
        String token28 = "API@CJ3984526@CJ:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjIwMyIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlsQitDbDAwc3Zhd3E4RG96NUFNOW5EZldWQjg1NFpxRG9rWkhFb3YxUlRYemt0UUQvTUtLZ0tBMzVrUVVpcFBxSjIvQlFZOWxsNTdVOWdKNk1YeUpFRjlleVVzb3FwV05RQm5OUWpOblBTN2U4dWgvMXZrNS9kSWtRdVFoQnk2dnZIOTRkc3JFWC85MEZicElmbWYwQ01jTjdKVUpzbkxtbVoxS29mQzdNSUZsN3ZvcTNleUw0bzVKc2gzUzhTVDV2Syt3SUQ3eHVId2JIaFc5dGkrbVowdXk1ZEhDeHFKS1JmeUd0U0lDdmdtQ3o0T2VkMytZd3Q4TjBmRWY2ZlpoL0NpdkdmYmJuY1JwSzYxMWc4dGQrV3p0ZzJlNHc4dnpFZHJXZXdxc1BubkZneWhBR1AwN3lDUzhGcGZPTmMyaXc9PSIsImlhdCI6MTczODM0MzU3MH0.k0ZpPEzIomBKMXQ-WQW1h3JAoHBXxz0HKZ9VoFRHQVc";

        try {
 /*           int n=0;
            Statement stmt = null;
           while (true) {*/
        Statement stmt = getStatement();
              /*  if (stmt == null) {
                    resetModem();
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    break;
                }
            }*/
            List<String> allTokens = new ArrayList<>();
            allTokens.add(token);
            allTokens.add(token2);
            allTokens.add(token3);
            allTokens.add(token4);
            allTokens.add(token5);
            allTokens.add(token6);
            allTokens.add(token7);
            allTokens.add(token8);
            allTokens.add(token9);
            allTokens.add(token10);
            allTokens.add(token11);
            allTokens.add(token12);
            allTokens.add(token13);
            allTokens.add(token14);
            allTokens.add(token15);
            allTokens.add(token16);
            allTokens.add(token17);
            allTokens.add(token18);
            allTokens.add(token19);
            allTokens.add(token20);
            allTokens.add(token21);
            allTokens.add(token22);
            allTokens.add(token23);
            allTokens.add(token24);
            allTokens.add(token25);
            allTokens.add(token26);
            allTokens.add(token27);
            allTokens.add(token28);
            Collections.reverse(allTokens);
/*            ResultSet rs = stmt.executeQuery("select id,category_id from category3;");
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                String categoryID = rs.getString("category_id");
                Long id =rs.getLong("id");
                category.categoryId=categoryID;
                category.id=id;
                categories.add(category);
            }*/

/*            for (Category category:categories
                 ) {*/
                //List<ListCj> tempLis = new ArrayList<>();
                Gson gson = new Gson();
                Thread.sleep(1000);
                //Unirest.setTimeouts(0, 0);
                HttpResponse<String> response = null;
                CjProductList cjProductList = null;
                try {
                    response = Unirest.get("https://developers.cjdropshipping.com/api2.0/v1/product/list?pid="+pidInp)
                            .header("CJ-Access-Token", allTokens.get(0))
                            .asString();
                   // if (response == null) continue;
                    System.err.println(response.getBody());
                    cjProductList = gson.fromJson(response.getBody(), CjProductList.class);
                    //if (cjProductList.getData() != null) tempLis.addAll(cjProductList.getData().getList());
                    ListCj listCj = cjProductList.getData().getList().get(0);
                    ResultSet rs2 = stmt.executeQuery("select id from category3 where category_id='"+listCj.categoryId+"';");
                    Long id = null;
                    while (rs2.next()) {
                         id = rs2.getLong("id");
                    }
                    System.out.println("----------------------------");

                    ResultSet rs3 = stmt.executeQuery("select * from product_mapping where pid='"+listCj.pid+"';");

                    if(!rs3.next()) {
                        String sql = "insert into product_mapping (category_id,create_date,image,live,max_price,min_price,origin_price,pid,product_name,shipping_country_codes,update_date,sta_tus)\n" +
                                "values" +
                                "(" +
                                id +
                                ","
                                + null +
                                "," + "'" +
                                listCj.productImage +
                                "'," +
                                true +
                                "," +
                                null +
                                ","
                                + null +
                                "," +
                                "'"
                                + listCj.sellPrice +
                                "'," +
                                "'"
                                + listCj.pid +
                                "'," +
                                "'"
                                + listCj.productNameEn.replace("'", "''") +
                                "'," +
                                "'"
                                + listCj.shippingCountryCodes.get(0) +
                                "',"
                                + null +
                                "," +
                                true +
                                ")";
                        try {
                            stmt.executeUpdate(sql);
                            System.out.println("mapping added");
                        } catch (Exception e) {
                            System.out.println("product error");
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("product mapping already exists!!!");
                    }

                    OfferProduct.offerProductImport(listCj.pid);

                } catch (Exception e) {
                    e.printStackTrace();
                    cjProductList = null;
                    Thread.sleep(5000);
                }
           // }









        }catch (Exception e){

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

    public static void resetModem () {
        WebDriver driver = new FirefoxDriver();
        try {
            System.out.println("start");
            driver.get("http://192.168.1.1/login.html");
            WebElement emailInput = driver.findElement(By.id("login_uname"));
            emailInput.clear();
            //Enter Text
            String username = "admin";
            emailInput.sendKeys(username);
            WebElement emailInput2 = driver.findElement(By.id("login_password"));
            emailInput2.clear();
            //Enter Text
            String pass = "Taknet15431543";
            emailInput2.sendKeys(pass);
            Thread.sleep(1000);
            WebElement submit = driver.findElement(By.id("btn_login"));
            submit.click();
            Thread.sleep(1000);
            WebElement setting = driver.findElement(By.id("menuSettings"));
            setting.click();
            Thread.sleep(1000);
            WebElement reboot = driver.findElement(By.id("submenu_sys99"));
            reboot.click();
            Thread.sleep(1000);
            WebElement ckeck = driver.findElement(By.id("reboot_checked_enable"));
            ckeck.click();
            Thread.sleep(1000);
            WebElement btnReboot = driver.findElement(By.id("btnReboot"));
            btnReboot.click();
            Thread.sleep(90000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
            System.out.println("an error");
        }
    }
}
