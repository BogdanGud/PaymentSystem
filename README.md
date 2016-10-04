This program calculates the cost of travel on toll roads and sends an email with corresponding information to the client, when he leaves pay zone. The clients registered in payment system and have the unique number. Checkpoint sends all info to the server : 
“o 192.168.2.125 : 9898” (your server IP-adress and port)

“{"clientId" : 5, "direction" : "in", "date" : 1201851200000, "checkpointId" : 1, "price" : 1, "email" : "bogdan.gudzulyak@gmail.com"}”

“{"clientId" : 5, "direction" : "out", "date" : 1201871200000, "checkpointId" : 7, "price" : 7, "email" : "bogdan.gudzulyak@gmail.com"}”

Important: for testing use one packet like one string, without “new line” characters.

When server receives second packet with "direction" : "out", the program calculates and sends an email with info about amount for payment, entry time and check-out time. For example:

![Alt text](http://i.imgur.com/U37UDj5.png "Mail example")




