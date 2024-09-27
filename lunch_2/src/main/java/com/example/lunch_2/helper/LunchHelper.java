package com.example.lunch_2.helper;

import com.example.lunch_2.entity.Lunch;
import com.example.lunch_2.form.LunchForm;

/**
 * ランチ：ヘルパー
 */
public class LunchHelper {
    /**
     * ランチへの変換
     */
    public static Lunch convertLunch(LunchForm form) {
        Lunch lunch = new Lunch();
        lunch.setId(form.getId());
        lunch.setMenu(form.getMenu());
        lunch.setShop(form.getShop());
        lunch.setPrice(form.getPrice());
        lunch.setRecentDate(form.getRecentDate());
        lunch.setTimes(form.getTimes());
        lunch.setRate(form.getRate());
        lunch.setMemo(form.getMemo());        
        return lunch;
    }

    /**
     * LunchFormへの変換
     */
    public static LunchForm convertLunchForm(Lunch lunch) {
        LunchForm form = new LunchForm();
        form.setId(lunch.getId());
        form.setMenu(lunch.getMenu());
        form.setShop(lunch.getShop());
        form.setPrice(lunch.getPrice());
        form.setRecentDate(lunch.getRecentDate());
        form.setTimes(lunch.getTimes());       
        form.setRate(lunch.getRate());  
        form.setMemo(lunch.getMemo());      
        // 更新画面設定
        form.setIsNew(false);
        return form;
    }
}