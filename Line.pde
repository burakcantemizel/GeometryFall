class Line{
    float positionX;
    float positionY;
    float point1X;
    float point1Y;
    float point2X;
    float point2Y;
    int colorType;
    color fillColor;
    float speed;

    Line(int colorType,float positionX, float positionY){
        this.positionX = positionX;
        this.positionY = positionY;
        this.colorType = colorType;

        point1X = random(positionX-10, positionX+10);
        point1Y = random(positionY-10, positionY+10);
        point2X = random(positionX-10, positionX+10);
        point2Y = random(positionY-10, positionY+10);

        while(dist(point1X,point1Y,point2X,point2Y) < 10){
            point1X = random(positionX-10, positionX+10);
            point1Y = random(positionY-10, positionY+10);
            point2X = random(positionX-10, positionX+10);
            point2Y = random(positionY-10, positionY+10);
        }

        //Burada çizginin uzunluğu hesaplanıp belirli bir uzunluktan kısaysa tekrar random atılacak.

        //this.colorType = (int)random(0,3);

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

        pushStyle();
            stroke(fillColor);
            strokeWeight(6);
            line(point1X, point1Y, point2X, point2Y);
        popStyle();

    }

    void movement(){
        positionY += speed;
        point1Y += speed;
        point2Y += speed;
    }

}
