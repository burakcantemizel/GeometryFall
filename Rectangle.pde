class Rectangle{
    float positionX;
    float positionY;
    float sizeX;
    float sizeY;
    int colorType;
    color fillColor;
    float speed;

    Rectangle(int colorType,float positionX, float positionY, float sizeX, float sizeY){
        this.positionX = positionX;
        this.positionY = positionY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.colorType = colorType;

        if(colorType == 0){ //Red
            fillColor = color(192, 57, 43, random(150,255));
        }else if(colorType == 1){ //Green
            fillColor = color(46, 204, 113, random(150,255));
        }else if(colorType == 2){ //Blue
            fillColor = color(52, 152, 219, random(150,255));
        }
        
        speed = random(1.2,1.8);
    }

    void draw(){
        movement();
        fill(fillColor);
        rect(positionX, positionY, sizeX, sizeY);
    }

    void movement(){
        positionY += speed;
    }

}
