## Apply the MVC design pattern to design the app
- Model
    + `MineGround`: 
        * keep the status of the game only
        * no exception thrown here, all handled in controller
        * no business logic here
- View
    + `MineSweepView`
        * the data that should be sent to the front-end
        * since the logic here is simple, just send a copy of mineGround
- Interface: the front-end page, can be rendered with the view data
    + `WelcomeInterface`
    + `GameInterface`: require view data
    + `GameOverInterface`: require game status
- Controller
    + `MineSweepController`
        * All the business logic code is here
- Utils
    + `generateMines`
        * this code should not be in the controller, since controller should not know where the mines are
        * this code should not be in the model, since the model is kept away from all business logic
        * therefore, we can only put it in the utils
    + `UNSPOTTED`, `MARKED`, `BOOMED`
        * this 3 status should better be in the model
        * however, the view also need this information
        * in order to decouple the view and model, i put these three here
