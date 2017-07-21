It'a simple android studio project so just need to the imort project.

Points:
there are three packages for java :
- bean - it contain the bookbean which is serializable class for book object where title = booktitle, imageURL = book image, and author= author name if it has.
- adapter - to bind boolist to the listview
- downloader - asynctask to get the json.

1> For Image Download I used Picasso .
2> Point I coverd - geting json from url through URL.openconnection() and then binding it to listview through the array adapter 


Future:
1> we can update some UI.
2> we can also use some third part library for networking.
3> For better result we can add refreshlistview (every time it load specific numbers of result)
	