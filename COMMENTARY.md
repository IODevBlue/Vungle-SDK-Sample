SIGN UP PROCESS
===============
Site: https://app.vungle.com/signup/

Commendations
-------------
- I got the welcoming email from `dashboard.support@vungle.com` to confirm my account and another email from `no-reply@vungle.com` with my account verification code.
- Then given access to my publisher page via https://publisher.vungle.com/
- Sign up process was standard.

Problems
--------
- Overall, no problems encountered during the signup phase.

Bot Performance
---------------
Sign-up process was simple and straight forward. There was no need to query the bot for assistance.


INTEGRATING THE SDK - ANDROID
=============================
Site: https://support.vungle.com/hc/en-us/articles/360002922871-Integrate-Vungle-SDK-for-Android-or-Amazon

Commendations
-------------
- Integrating ads in code while following the instructions from the documentations and from the sample Github repository was straight-forward.

Problems
--------
- The link to [register an app](https://support.vungle.com/hc/en-us/articles/360021968731) navigates to [Vungle Zendesk](https://vungle.zendesk.com/) which requires a separate account different from the [sign up](https://app.vungle.com/signup/) page. After this, it redirects to an Error 404 site saying "You're not authorized to access this page". The instructions to register an app to Vungle is not available on the documentations site.
- The documentations references an outdated version (7.4.0) of the Vungle SDK. Latest version is 7.4.1 
- The documentations references deprecated `BannerAd` which is replaced by `VungleBannerView`. [Integrate Banner Ads](https://support.vungle.com/hc/en-us/articles/360048256211-Integrate-Banner-Ads#h_01H82J3KDMD34YWEQBF9TMAV8Z)
- I keep getting "No advertisements are available for your current bid. Please try again later.". Is there an official article for possible errors? The bot did well to explain all errors I encountered.
- I would admit that the documentations used codes directly from the sample Github which referenced classes and variables **NOT** available in the SDK. Take for example, [Integrate Native Ads](https://support.vungle.com/hc/en-us/articles/18091437567899-Integrate-Native-Ads#h_01H8F28H76XPN4DW3NABFTGWV9)
which references a `NativeAdFragment` which is used without context. This `NativeAdFragment` is a file in the [sample github repository](https://github.com/Vungle/VungleAds-SDK/blob/master/Samples/android/Kotlin/app-v7-kotlin/src/main/java/com/vungle/samples/samplekotlin/NativeAdFragment.kt) not an official class file in the Vungle SDK. This would confuse a **LOT** of native developers.

The technical writer should instead use a code block with inline code comments like so:
```kotlin
var nativeAd: NativeAd? = null //Native ad instance.

nativeAd = NativeAd(context, placementId).apply {
   adListener = object: NativeAdListener {} //Your native ad listener interface
   load()
}
```
or instead provide a hyperlink to the sample class file that used that particular code block. For example, the code block above directly references line 29 of the `NativeAdFragment`. So the technical writer can provide a hyperlink that redirects to that line like so:
"Refer to the sample implementation [here](https://github.com/Vungle/VungleAds-SDK/blob/1b6a8fca1f41d94dc1b17890b6d418983734f80c/Samples/android/Kotlin/app-v7-kotlin/src/main/java/com/vungle/samples/samplekotlin/NativeAdFragment.kt#L29). This would be helpful to a **LOT** of developers.

Bot Performance
---------------
- Surprisingly, the bot did fairly well. From giving step-by-step procedures, code fragments to helpful concise replies. I'd give a solid 8.5/10. It was helpful in answering most questions that I could solve on my end from my publisher page.