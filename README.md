# cowin-bot app
Provides a messenger to send notification about covid vaccine availablity in a particular district

# Setup
Create a telegram bot and a group to send messages. Find their identifiers to configure the application.
* Register a bot with telegram
  * Go to Telegram app
  * Search for @BotFather
  * Type /newbot
  * Provide a name
  * Copy the API token
* Create a Telegram Group to send message
* Chat id for the new group
  * Make the registered bot a member of the group with permissions to send message
  * Send a dummy message on the group chat 
  * Calling API https://api.telegram.org/bot[APIToken]/getUpdates
  * Look for "chat" in the reponse. The id should be a negative number
* Change the group permission so that only bot can send messages

Find out the infromation of district from cowin APIs.
* Find out the district id
  * Find the state id by calling https://cdn-api.co-vin.in/api/v2/admin/location/states
  * Use the state id to find district by calling API https://cdn-api.co-vin.in/api/v2/admin/location/districts/{state-id}
  
#Configure Application
* Create a property file as per src/resources/samples.properties
* Fill the information collectected in the last step
* Build the application by running mvn install from the root folder
* Run the application using the following command
  * java -Dvaccine.finder.config.file=[property file location] -jar target/telegram-1.0.jar
