# Woke-Android
#1 new notes as of 3/29/2017

If you have trouble getting the gradle to sync do these steps

  * On Android studio, go to the menu bar and click on Tools > Android > SDK Manager.
  *  Click on the SDK Tools tab
  *  check the Google Play services box as well as the Google Repository box.
  *  Click on apply, it will prompt you before applying the new updates.
  *  Click Ok and wait for the download
  *  Sync your project again and everything is fine.

----------------------------------------------------------------------------------------

My website is: [104.198.148.208:8000](http://104.198.148.208:8000)

(if you are curious about it, the repo is public and called Woke. go ahead and check it out)

I will be creating pages throughout the semester that we can query from

I currently have 2 pages up for our queries. I will add to this list as they become available.
* [104.198.148.208:8000/members](http://104.198.148.208:8000/members)
* [104.198.208.8000/bills](http://104.198.148.208:8000/bills)

if you look at members you will see that there are only 2 members in that list. I currently have the app correctly collecting this data and displaying it if you check it out.

there are many more entries under the bills url. However, I discoverd there is a limit to how much we can query from the page. If you tried to query this page, the response.body() data returned is incomplete making it incorrect json and impossible to parse.

one cool thing to note about the cards I implemented:
* when you click on them they turn green.

android has a library for displaying cards. I had trouble getting that approach to work so I went with this one. there may be a better way to accomplish this and I am open to suggestions.

be sure to check out the code, I left comments everywhere to help guide you.

hit me up with questions or concerns.

a couple of notes:
* we are using retrofit2.0.2 to gather information from my site
* we are using JsonObjects NOT JSONObjects. they come from different libraries. and yes it matters.
* check out "projects" tab on github under this repo. There you can create tasks and drag and drop them into different buckets.
* [tutorial](http://javapapers.com/android/android-cards-list-view/) I followed to create the cards.
* [Android cards](https://developer.android.com/reference/android/support/v7/widget/CardView.html) this failed me but may be worth trying again
