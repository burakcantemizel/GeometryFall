class Circle{
    float positionX;
    float positionY;
    float radius;
    int colorType;
    color fillColor;
    float speed;

    Circle(int colorType,float positionX, float positionY, float radius){
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
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
        ellipse(positionX, positionY, radius * 2, radius * 2);
    }

    void movement(){
        positionY += speed;
    }

}
