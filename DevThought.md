## Apply the MVC design pattern to design the app
- Model
    + `MineGround`: 
        * keep the status of the game only
        * no exception thrown here, all handled in controller
        * no business logic here
        * only keep the place of the mines, no matrix here needed
    + `GameStatus`
    + `GameMove`
    + `GameOperation`
- View
    + `MineSweeperView`
        * the data that should be sent to the front-end
        * since the logic here is simple
        * This is the only position to keep the matrix
        * Operation are checked with the view
- Interface: the front-end page, can be rendered with the view data
    + `WelcomeInterface`
    + `GameInterface`: require view data
    + `GameOverInterface`: require game status
- Controller
    + `MineSweeperController`
        * All the business logic code is here
        * Have only one general entry point `makeMove`
        * The move validation is tested against the view
        * The game status is test against the model
- Utils
    + `generateMines`
        * this code should not be in the controller, since controller should not know where the mines are
        * this code should not be in the model, since the model is kept away from all business logic
        * therefore, we can only put it in the utils
    + `UNSPOTTED`, `MARKED`, `BOOMED`
        * this 3 status should better be in the model
        * however, the view also need this information
        * in order to decouple the view and model, i put these three here
    + `ITERATE_SEQ`: define the way to iterate the grounds around
- IOUtils
    + Responsible for reading input



## A way to implement the workflow - try until no exception

```java
boolean isSuccess = false;
while (!isSuccess) {
    try {
        someMethod();
        isSuccess = true;
    } catch (Exception e) {
        e.printStackTrace();
    }
return getResult();
```



