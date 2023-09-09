<h1>From the last Android application, I have learned several things...</h1>
<span>How to write the local image source in a JSON file, to display it in ImageView Android Kotlin
use=> getIdentifier() method of the Resources class
We can convert a drawable resource ID from a string to an integer value, the same as using /R.drawable."imageName"/ in setting image src.</span>
<h3>Example:</h3>

```
```
val drawableName = "ic_image" // Name of the drawable resource
val resId = resources.getIdentifier(drawableName, "drawable",packageName)
```
```

<h6>The "packageName" parameter is the package name of your app.</h6>

<p>After that, we can set the image using the setImageResource() method of an ImageView object, as shown in the code image below.
</p>
<h6>Note: The image source is stored in a JSON file like "ic_egypt".</h6>

---

<h3>Application features:</h3>
<p>- Store the rendering data in a JSON file.</p>
<p>- Use BindingView as a best practice</p>
<p>- Count the user's correct, and wrong answers and display them in different colors.</p>

---
<h3>Video</h3>

https://github.com/HADEER-ESS/QuizApp/assets/95582508/4dd22fae-fe9e-4938-952f-2261faf938a6

