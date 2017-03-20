# Woke-Android

My website is: [104.198.148.208:8000](104.198.148.208:8000)

(if you are curious about it, the repo is public and called Woke. go ahead and check it out)

I will be creating pages throughout the semester that we can query from

I currently have 2 pages up for our queries. I will add to this list as they become available.
* [104.198.148.208:8000/members](http://104.198.148.208:8000/members)
* [104.198.208.8000/bills](http://104.198.148.208:8000/bills)

if you look at members you will see that there are only 2 members in that list. I currently have the app correctly collecting this data and displaying it if you check it out.

there are many more entries under the bills url. However, I discoverd there is a limit to how much we can query from the page. If you tried to query this page, the response.body() data returned is incomplete making it incorrect json and impossible to parse.

one cool thing to note about the cards I implemented:
* when you click on them they turn green.

android has a library for displaying cards. I had trouble getting that approach to working so I went with this one. there may be a better way to accomplish this and I am open to suggestions.

be sure to check out the code, I left comments everywhere to help guide you.

hit me up with questions or concerns.

a couple of notes:
* we are using retrofit2.0.2 to gather information from my site
* we are using JsonObjects NOT JSONObjects. they come from different libraries. and yes it matters.
* check out "projects" tab on github under this repo. There you can create tasks and drag and drop them into different buckets.
