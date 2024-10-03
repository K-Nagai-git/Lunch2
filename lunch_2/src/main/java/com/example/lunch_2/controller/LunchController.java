package com.example.lunch_2.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.lunch_2.entity.Lunch;
import com.example.lunch_2.form.LunchForm;
import com.example.lunch_2.helper.LunchHelper;
import com.example.lunch_2.service.LunchService;

import lombok.RequiredArgsConstructor;

/**
 * Lunch：コントローラー
 */
@Controller
@RequestMapping("/lunches")
@RequiredArgsConstructor
public class LunchController {

	/** DI */
	private final LunchService lunchService;

	/**
	 * 「ランチ」の一覧を表示します。
	 */
	@GetMapping
	public String list(Model model) {
		model.addAttribute("lunches", lunchService.findAllLunch());
		return "lunch/list";
	}

	/**
	 * 指定されたIDの「ランチ」の詳細を表示します。
	 */
	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model,
			RedirectAttributes attributes) {
		// 管理IDに対応する「ランチ」情報を取得
		Lunch Lunch = lunchService.findByIdLunch(id);
		if (Lunch != null) {
			// 対象データがある場合はモデルに格納
			model.addAttribute("lunch", lunchService.findByIdLunch(id));
			return "lunch/detail";
		} else {
			// 対象データがない場合はフラッシュメッセージを設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// リダイレクト
			return "redirect:/lunches";
		}
	}
	// ▽▽▽▽▽ 13.7追加 ▽▽▽▽▽
	// === 登録・更新処理追加 ===
	/**
	 * 新規登録画面を表示します。
	 */
	@GetMapping("/form")
	public String newLunch(@ModelAttribute LunchForm form) {

		// 新規登録画面の設定
		form.setIsNew(true);
		return "lunch/form";
	}

	/**
	 * 新規登録を実行します。
	 */
	@PostMapping("/save")
	public String create(LunchForm form,RedirectAttributes attributes) {
		// エンティティへの変換
        // 文字列を LocalDate に変換
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2024-01-01", formatter);
        // 変換した LocalDate を recentDate にセット
        form.setRecentDate(date);
		form.setTimes(0);
		Lunch Lunch = LunchHelper.convertLunch(form);
		// 登録実行
		lunchService.insertLunch(Lunch);
		// フラッシュメッセージ
		attributes.addFlashAttribute("message", "新しいランチが登録されました");
		// PRGパターン
		return "redirect:/lunches";
	}

	/**
	 * 指定されたIDの修正画面を表示します。
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model,
			RedirectAttributes attributes) {
		// IDに対応する「ランチ」を取得
		Lunch target = lunchService.findByIdLunch(id);
		if (target != null) {
			// 対象データがある場合はFormへの変換
			LunchForm form = LunchHelper.convertLunchForm(target);
			// モデルに格納
			model.addAttribute("lunchForm", form);
			return "lunch/form";            
		} else {
			// 対象データがない場合はフラッシュメッセージを設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 一覧画面へリダイレクト
			return "redirect:/lunches";            
		}
	}

	/**
	 * 「ランチ」の情報を更新します。
	 */
	@PostMapping("/update")
	public String update(LunchForm form, 
			RedirectAttributes attributes) {
		// エンティティへの変換
		Lunch Lunch = LunchHelper.convertLunch(form);
		// 更新処理
		lunchService.updateLunch(Lunch);
		// フラッシュメッセージ
		attributes.addFlashAttribute("message", "ランチが更新されました");

        // ★フォームデータが正しくマッピングされているか確認
        System.out.println("Recent Date: " + form.getRecentDate()); 	
		
		// PRGパターン
		return "redirect:/lunches";
	}
	// △△△△△ 13.7追加 △△△△△
	
	/**
	 * 指定されたIDの前回利用日を更新します。
	 */
	@PostMapping("/today/{id}")
	public String today(@PathVariable Integer id, Model model,
			RedirectAttributes attributes) {
	
		// IDに対応する「ランチ」を取得-1       
		Lunch target = lunchService.findByIdLunch(id);
		int times=target.getTimes()+1;
		System.out.println("times "+times);
		
		 // ★今日の日付を取得
        LocalDate today = LocalDate.now();
        // ★サービスでランチのrecentDateを今日に更新
        lunchService.updateRecentDate(id, today, times);

		// IDに対応する「ランチ」を取得-2       
		target = lunchService.findByIdLunch(id);

		if (target != null) {
			
			// 対象データがある場合はFormへの変換
			LunchForm form = LunchHelper.convertLunchForm(target);
			System.out.println("form "+form.getTimes());
						
			// モデルに格納
			model.addAttribute("lunchForm", form);

			// エンティティへの変換
			Lunch Lunch = LunchHelper.convertLunch(form);
			// 更新処理
			lunchService.updateLunch(Lunch);
			// フラッシュメッセージ
			attributes.addFlashAttribute("message", "利用日が更新されました");
			// PRGパターン
			return "redirect:/lunches";

		} else {
			// 対象データがない場合はフラッシュメッセージを設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 一覧画面へリダイレクト
			return "redirect:/lunches";            
		}
	}
	
	// ▽▽▽▽▽ 13.11追加 ▽▽▽▽▽
	/**
	 * 指定されたIDの「ランチ」を削除します。
	 */
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
		// 削除処理
		lunchService.deleteLunch(id);
		// フラッシュメッセージ
		attributes.addFlashAttribute("message", "ランチが削除されました");
		// PRGパターン
		return "redirect:/lunches";
	}
	// △△△△△ 13.11追加 △△△△△
}