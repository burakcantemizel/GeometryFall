class Player{
    String type;
    float positionX;
    float positionY;
    float radius;
    int colorType;
    color fillColor;
    String movementType;

    Player(String type, float positionX, float positionY){
        this.type = type;
        this.positionX = positionX;
        this.positionY = positionY;
        radius = 60;
        colorType = 3;
        fillColor = color(0,0,0,180);
    }

    void draw(){
        radius += 0.008;

        if(radius >= 60){
            radius = 60;
        }

        if(radius <= 10){
            setup();
        }

        switch(type){
            case "circle":
                movement();
                fill(fillColor);
                ellipse(positionX, positionY, radius * 2, radius * 2);
                break;
        }
    }

    void movement(){
      if(movementType == "mouse"){
        positionX = mouseX;
        positionY = mouseY;
      }        

    }

}
