/**
 * 計算式を計算し、結果を表示する
 * 引数：クリックした「計算」ボタンの要素
 */
function calc(obj) {
	// 基準値
	var base = parseInt(document.formBase.base.value);
	// 登録した計算式
	var formula = obj.parentNode.parentNode.cells[2].innerText;
	// JavaScriptで計算するために整形
	var formulaStr = "var x = " + base + ";";
	formulaStr += formula;
	// 計算結果を設定
	obj.parentNode.parentNode.cells[4].innerText = eval(formulaStr);
}