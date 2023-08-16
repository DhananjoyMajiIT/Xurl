package com.crio.shorturl;

import java.security.SecureRandom;
import java.util.HashMap;

public class XUrlImpl implements XUrl{


    HashMap<String,String>url=new HashMap<>();
    HashMap<String,String>rurl=new HashMap<>();
    HashMap<String,Integer>hit=new HashMap<>();

     @Override
    public String registerNewUrl(String longUrl){
        String shortUrl;

        final String SOURCE="ABCDEFGHIJKLMNOPQUSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder sb=new StringBuilder(9);
        SecureRandom seq=new SecureRandom ();

        for(int i=0;i<9;i++)
         sb.append(SOURCE.charAt(seq.nextInt(SOURCE.length())));

         shortUrl="http://short.url/"+sb.toString();

         if(rurl.containsKey(shortUrl)){
            return null;
         }

         url.put(longUrl,shortUrl);
         rurl.put(shortUrl,longUrl);

        if(hit.containsKey(longUrl)){
            hit.put(longUrl,hit.get(longUrl)+1);
        }
        else{
            hit.put(longUrl,1);
        }
        return shortUrl;
    }


    
    @Override
    public String getUrl(String shortUrl){
        return rurl.get(shortUrl);
    }

    @Override
    public  Integer getHitCount(String longUrl){
        if(hit.containsKey(longUrl)){
            return hit.get(longUrl);
        }
        return 0;
    }
    @Override
    public String registerNewUrl(String longUrl, String shortUrl){

        if(rurl.containsKey(shortUrl)){
            return null;
        }

        url.put(longUrl,shortUrl);
        rurl.put(shortUrl,longUrl);
        if(hit.containsKey(longUrl)){
            hit.put(longUrl,hit.get(longUrl)+1);
        }
        else{
            hit.put(longUrl,1);
        }
        
        return shortUrl;
    }

   @Override
    public String delete(String longUrl){

        String res=url.get(longUrl);
       return rurl.remove(res);

    }

}
