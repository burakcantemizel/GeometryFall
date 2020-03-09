class Triangle{
    float positionX;
    float positionY;
    int colorType;
    color fillColor;
    float speed;
    PVector[] vertices;

    Triangle(int colorType,float positionX, float positionY){
        this.positionX = positionX;
        this.positionY = positionY;
        this.colorType = colorType;
        vertices = new PVector[3];

        vertices[0] = new PVector(random(positionX-20,positionX+20),random(positionY-20,positionY+20));
        vertices[1] = new PVector(random(positionX-20,positionX+20),random(positionY-20,positionY+20));
        vertices[2] = new PVector(random(positionX-20,positionX+20),random(positionY-20,positionY+20));
        
        //println(vertices[0] + " " + vertices[1] + " " +vertices[2]);
        //println(vertices[0].mag() + " " + vertices[1].mag() + " " +vertices[2].mag());

        float area = 0.5 * abs( vertices[0].x * vertices[1].y + vertices[1].x * vertices[2].y + vertices[2].x * vertices[0].y - ( vertices[0].y * vertices[1].x + vertices[1].y * vertices[2].x + vertices[2].y * vertices[0].x ) );
        while(area < 150){
            vertices[0] = new PVector(random(positionX-20,positionX+20),random(positionY-20,positionY+20));
            vertices[1] = new PVector(random(positionX-20,positionX+20),random(positionY-20,positionY+20));
            vertices[2] = new PVector(random(positionX-20,positionX+20),random(positionY-20,positionY+20));
            area = 0.5 * abs( vertices[0].x * vertices[1].y + vertices[1].x * vertices[2].y + vertices[2].x * vertices[0].y - ( vertices[0].y * vertices[1].x + vertices[1].y * vertices[2].x + vertices[2].y * vertices[0].x ) );
        }


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
        
        
        beginShape();
        fill(fillColor);
        for(PVector v: vertices){
            vertex(v.x, v.y);
        }
        endShape();
    }

    void movement(){
        positionY += speed;
        vertices[0].add(0,speed);
        vertices[1].add(0,speed);
        vertices[2].add(0,speed);
    }

}
