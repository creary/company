# CSS权重

## 权重值的计算

关于权重计算，网上有

1. 从CSS代码的所处位置来看CSS权重优先级: 
>  `内嵌样式` > `内部样式表` > `外部样式表`。

2. 从样式选择器来看CSS权重优先级:
> **`!important`** > **内嵌样式 **> **ID选择器（`#id`）** > **class选择器（`.class`）** > **标签、伪类、属性选择器** > **伪元素** > **通配符（`*`）** >**继承（`inherit`）**。

以上
W3

>1.  count 1 if the declaration is from is a 'style' attribute rather than a rule with a selector, 0 otherwise (= a) (In HTML, values of an element's "style" attribute are style sheet rules. These rules have no selectors, so a=1, b=0, c=0, and d=0.)
>2. count the number of ID attributes in the selector (= b)
>3. count the number of other attributes and pseudo-classes in the selector (= c)
>4. count the number of element names and pseudo-elements in the selector (= d)

翻译成中文大意如下：

1. **如果规则是写在标签的style属性中（内联样式）**，则A=1，否则，A=0。 对于内联样式，由于没有选择器，所以 B、C、D 的值都为 0，即 A=1, B=0, C=0, D=0（简写为 1,0,0,0，下同）。
2. **计算该选择器中ID的数量**。（例如，#header 这样的选择器，计算为 0, 1, 0, 0）。
3. **计算该选择器中伪类及其它属性的数量（包括类选择器、属性选择器等，不包括伪元素）**。 （例如， .logo[id='site-logo'] 这样的选择器，计算为 0, 0, 2, 0）。
4. **计算该选择器中伪元素及标签的数量**。（例如，p:first-letter 这样的选择器，计算为0, 0, 0, 2）。

W3C还给出了更为详细的实例解析：
```
 *             {}  /* a=0 b=0 c=0 d=0 -> specificity = 0, 0, 0, 0 */
 li            {}  /* a=0 b=0 c=0 d=1 -> specificity = 0, 0, 0, 1 */
 li:first-line {}  /* a=0 b=0 c=0 d=2 -> specificity = 0, 0, 0, 2 */
 ul li         {}  /* a=0 b=0 c=0 d=2 -> specificity = 0, 0, 0, 2 */
 ul ol+li      {}  /* a=0 b=0 c=0 d=3 -> specificity = 0, 0, 0, 3 */
 h1 + *[rel=up]{}  /* a=0 b=0 c=1 d=1 -> specificity = 0, 0, 1, 1 */
 ul ol li.red  {}  /* a=0 b=0 c=1 d=3 -> specificity = 0, 0, 1, 3 */
 li.red.level  {}  /* a=0 b=0 c=2 d=1 -> specificity = 0, 0, 2, 1 */
 #x34y         {}  /* a=0 b=1 c=0 d=0 -> specificity = 0, 1, 0, 0 */
 style=""          /* a=1 b=0 c=0 d=0 -> specificity = 1, 0, 0, 0 */
```

以上规则可以总结成下表：

| 选择器	| 表达式或者示例	| 说明	| 权重	|
|:---------:|:-----------------:|:------|:-----:|
| ID选择器	| `#idSelector`		|		| 100	|
| 类选择器	| `.class-selector`	|		| 10	|
| 标签选择器| `div`				| 		| 1     |

如果权重值一样
但是权重值不是10进制的也不会进位，不要以为11个`class`选择器就能抵得过一个`id`选择器。我们可以用以下代码作为测试：
```
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>11 class selector Vs 1 id selector</title>
	<style type="text/css">
	    /* 一个id 选择器 */
		#testId {
			color: yellow;
		}
		
		/* 11个class 选择器 */
		.test .test .test .test .test .test .test .test .test .test .test {
			color : red;
		}
	</style>
</head>
<body>
    <div class="test">
		<div class="test">
			<div class="test">
				<div class="test">
					<div class="test">
						<div class="test">
							<div class="test">
								<div class="test">
									<div class="test">
										<div class="test">
											<div class="test" id="testId">
												看我颜色
											</div><!-- 11个嵌套的 div -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
```

> 在某些浏览器中256个类选择器的却能干的过一个ID选择器，但是这只是一个`Bug`。
> 

参考资料&文档：
1. [W3.org Specificity](http://www.w3.org/TR/CSS2/cascade.html#specificity "Assigning property values, Cascading, and Inheritance")
2. [深入解析CSS样式层叠权重值](http://ofcss.com/2011/05/26/css-cascade-specificity.html)