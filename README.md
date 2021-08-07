# Availity

1. My proudest professional achievement has been taking ownership of multiple features. Reflecting on features I've "taken ownership" of shows me how far I have come since starting my career.

I was able to take ownership to implement a feature a client wanted regarding a tilt tray (A large conveyor that has trays on the conveyor that tilt to both sides.  The way the software worked was that in order to induct (place a stock keeping unit (SKU) onto the tilt tray - was to have a container that owned that sku.  If the SKU ever became orphaned(containerless) ,then it would require user interaction to be able to "reinduct" (find a container in the system, locate it physically, and then induct it -- VERY TIME CONSUMING)

I was able to make it so if there was demand for the SKU on the loop, it would go to a designated chute (destination for SKUs)
This reduced the number of pallets that had to be physically moved around the 1 million + square foot warehouse. (Reduced the # pallets by 90% from around 200 pallets a day to around 20)
Autobagger, Configurable Label Change, QuadChute (what I talked about above) to name a few I'm "Proud of" -- can mostly noting here incase asked in the future I can reference this.




2. I'm currently working my way through two books (really trying to make time as I'm not a big reader but it's something I'd like to spend more time doing.

The Intelligent Investor:
I've been investing since I was a teenager and looking to take a more stock based approach to my portfolio and it can be overwhelming when trying to "pick" good stocks.  This book is about "value" investing

The Pragmatic Programmer:
I am seeking a new role to grow as an individual contributor to reach my (current) 5-10 year plan of becoming a software architecture - I figured reading this highly recommended book couldn't hurt?



3. From my understanding of what "Availity" does (Which if I have a poor understanding then it would be hard to explain to my grandma so...)
Availity tries to bridge the gap between health care providers and the payers (I think that is the insurance companies but it could be direct consumer im unclear to be honest..)
By increasing visibility and bringing this process into the 21st century.



4. Use a stack, push if we see a (
   If we see a ) check if the stack is empty (if so then we have more closing than opening which is invalid, otherwise pop.
   If we see a non () then do nothing
   If we get to the end of the string and the queue is empty awesome its balanced, otherwise it isn't.

   This is a linear solution (Look at every element in the list and add it to the queue)
   This is a linear solution with memory as WORST case everything is an open paren (Improvement would be check if the stack size > remaining string.length to look at, return    early as false as BEST case they're all closing it still would be imbalanced. This would bring us to n/2 worst case which is stil linear.

   

5. This one is a little more confusing....

Map<String, PriorityQueue<Enrollees>>
The key for this is the company name.  
      Requirement: separate enrollees by insurance company in its own file (companyName will be fileName)

The comparator for the PriorityQueue is lastName and firstName this is in the Enrollees class Requirement
      Requirement: Additionally, sort the contents of each file by last and first name (ascending) (I think ascending == lexigraphical order - could be wrong)

Lastly - when adding to the priortyQueue - we check if we contain the enrollee - if we do, then we check the version and keep the higher one
      Requirement: if there are duplicate User Ids for the same Insurance Company, then only the record with the highest version should be included



Thoughts:
Wow this was so ambiguous I am imagining it was designed that way on purpose! It's done in a single pass so I think that's good.  (Code could probably be cleaned up and abstracted but I think this meets the criteria (if it isn't enough I'll be punching myself in the future...)

Biggest gripe I have is using a priorityQueue and then if the userId and Company is contaiend in there (equals of enrollee), then I need to traverse it and find the two enrollees. If the incoming enrollee has a higher version, then I remove the one in the queue and add the new one.

Time complexity - we traverse the list once so it's linear, WORST case scenario is everything is the same company, for the same User ID.
Then I think it's n^2 because im having to traverse the priorityQueue to do the swap on the Version.

Space complexity is linear with some constant for the size of a hashmap and priority queue.  Worse case would probably be unique Companies to increase the size of the hashMap holding the priorityQueues but I'm getting tired.


Closing thoughts: 
I enjoyed this and hope to hear from the team.

Thanks!

