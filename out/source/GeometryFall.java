import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class GeometryFall extends PApplet {

int gameWindowWidth;
int gameWindowHeight;

String gameState;

Player player;
ArrayList<Circle> circles;
ArrayList<Rectangle> rectangles;
ArrayList<Line> lines;
ArrayList<Triangle> triangles;

int timer;
float spawnerTimer;

float circleSpawnerX;
int circleSpawnerDirection;
float rectangleSpawnerX;
int rectangleSpawnerDirection;
float lineSpawnerX;
int lineSpawnerDirection;
float triangleSpawnerX;
int triangleSpawnerDirection;

int area1Color;
int area2Color;
int area3Color;

float touchX1;
float touchY1;
float touchX2;
float touchY2;

public void settings(){
    
    gameWindowWidth = 540;
    gameWindowHeight = 960;
    size(gameWindowWidth, gameWindowHeight, P2D);
}

public void setup(){
    orientation(PORTRAIT);
    //frameRate(60);
    noStroke();

    gameState = "menu";

    timer = 0;
    player = new Player("circle", gameWindowWidth / 2, gameWindowHeight / 2);
    circles = new ArrayList<Circle>();
    rectangles = new ArrayList<Rectangle>();
    lines = new ArrayList<Line>();
    triangles = new ArrayList<Triangle>();
    
    //PCDE DENERKEN BURALARI DÜZELT //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    player.movementType = "mouse"; //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    circleSpawnerX = 0;
    circleSpawner2X = 0 + (gameWindowWidth/8)*4;
    circleSpawnerDirection = 1;

    rectangleSpawnerX = 0 + gameWindowWidth/8;
    rectangleSpawner2X = 0 + (gameWindowWidth/8)*5;
    rectangleSpawnerDirection = 1;

    lineSpawnerX = 0 + (gameWindowWidth/8)*2;
    lineSpawner2X = 0 + (gameWindowWidth/8)*6;
    lineSpawnerDirection = 1;

    triangleSpawnerX = 0 + (gameWindowWidth/8)*3;
    triangleSpawner2X = 0 + (gameWindowWidth/8)*7;
    triangleSpawnerDirection = 1;

    area1Color = 0;
    area2Color = 1;
    area3Color = 2;
}

public void draw(){

    switch(gameState){
        case "menu":
            gameState = "game";
            break;
        
        case "game":
                drawBackground();

                timer += 1;
                spawnerTimer += random(2.4f,2.7f);

                colorController();
                circleSpawner();
                rectangleSpawner();
                lineSpawner();
                triangleSpawner();

                drawCircles();
                drawRectangles();
                drawLines();
                drawTriangles();
                                
                player.draw();
                
                drawGui();
            break;
    }

    
}

public void circleSpawner(){

    if(circleSpawnerDirection == 0 && circleSpawnerX > 0){
        circleSpawnerX -= 3;
        if(circleSpawnerX <= 0){
            circleSpawnerDirection = 1;
        }
    }

    if(circleSpawnerDirection == 1 && circleSpawnerX < gameWindowWidth){
        circleSpawnerX += 3;
        if(circleSpawnerX >= gameWindowWidth){
            circleSpawnerDirection = 0;
        }
    }

    if(timer % 10 == 0){
        if(returnArea(circleSpawnerX) == 0){
            circles.add(new Circle(area1Color,circleSpawnerX + random(-50,+50), -50, random(8, 12)));
            circles.add(new Circle(area1Color,circleSpawnerX + random(-50,+50), -50, random(8, 12)));
            circles.add(new Circle(area1Color,circleSpawnerX + random(-50,+50), -50, random(8, 12)));
        }else if(returnArea(circleSpawnerX) == 1){
            circles.add(new Circle(area2Color,circleSpawnerX + random(-50,+50), -50, random(8, 12)));
            circles.add(new Circle(area2Color,circleSpawnerX + random(-50,+50), -50, random(8, 12)));
            circles.add(new Circle(area2Color,circleSpawnerX + random(-50,+50), -50, random(8, 12)));
        }else if(returnArea(circleSpawnerX) == 2){
            circles.add(new Circle(area3Color,circleSpawnerX + random(-50,+50), -50, random(8, 12)));
            circles.add(new Circle(area3Color,circleSpawnerX + random(-50,+50), -50, random(8, 12)));
            circles.add(new Circle(area3Color,circleSpawnerX + random(-50,+50), -50, random(8, 12)));
        }
        
    }
}

public void rectangleSpawner(){
    if(rectangleSpawnerDirection == 0 && rectangleSpawnerX > 0){
        rectangleSpawnerX -= 3;
        if(rectangleSpawnerX <= 0){
            rectangleSpawnerDirection = 1;
        }
    }

    if(rectangleSpawnerDirection == 1 && rectangleSpawnerX < gameWindowWidth){
        rectangleSpawnerX += 3;
        if(rectangleSpawnerX >= gameWindowWidth){
            rectangleSpawnerDirection = 0;
        }
    }

    if(timer % 10 == 0){
        if(returnArea(rectangleSpawnerX) == 0){
            rectangles.add(new Rectangle(area1Color,rectangleSpawnerX + random(-50,+50), -50, random(14,18), random(14, 18) ));
            rectangles.add(new Rectangle(area1Color,rectangleSpawnerX + random(-50,+50), -50, random(14,18), random(14, 18) ));
            rectangles.add(new Rectangle(area1Color,rectangleSpawnerX + random(-50,+50), -50, random(14,18), random(14, 18) ));
        }else if(returnArea(rectangleSpawnerX) == 1){
            rectangles.add(new Rectangle(area2Color,rectangleSpawnerX + random(-50,+50), -50, random(14,18), random(14, 18) ));
            rectangles.add(new Rectangle(area2Color,rectangleSpawnerX + random(-50,+50), -50, random(14,18), random(14, 18) ));
            rectangles.add(new Rectangle(area2Color,rectangleSpawnerX + random(-50,+50), -50, random(14,18), random(14, 18) ));
        }else if(returnArea(rectangleSpawnerX) == 2){
            rectangles.add(new Rectangle(area3Color,rectangleSpawnerX + random(-50,+50), -50, random(14,18), random(14, 18) ));
            rectangles.add(new Rectangle(area3Color,rectangleSpawnerX + random(-50,+50), -50, random(14,18), random(14, 18) ));
            rectangles.add(new Rectangle(area3Color,rectangleSpawnerX + random(-50,+50), -50, random(14,18), random(14, 18) ));
        }
        
    }
}

public void lineSpawner(){
    if(lineSpawnerDirection == 0 && lineSpawnerX > 0){
        lineSpawnerX -= 3;
        if(lineSpawnerX <= 0){
            lineSpawnerDirection = 1;
        }
    }

    if(lineSpawnerDirection == 1 && lineSpawnerX < gameWindowWidth){
        lineSpawnerX += 3;
        if(lineSpawnerX >= gameWindowWidth){
            lineSpawnerDirection = 0;
        }
    }

    if(timer % 10 == 0){
        if(returnArea(lineSpawnerX) == 0){
            lines.add(new Line(area1Color,lineSpawnerX + random(-50,+50), -50));
            lines.add(new Line(area1Color,lineSpawnerX + random(-50,+50), -50));
            lines.add(new Line(area1Color,lineSpawnerX + random(-50,+50), -50));
        }else if(returnArea(lineSpawnerX) == 1){
            lines.add(new Line(area2Color,lineSpawnerX + random(-50,+50), -50));
            lines.add(new Line(area2Color,lineSpawnerX + random(-50,+50), -50));
            lines.add(new Line(area2Color,lineSpawnerX + random(-50,+50), -50));
        }else if(returnArea(lineSpawnerX) == 2){
            lines.add(new Line(area3Color,lineSpawnerX + random(-50,+50), -50));
            lines.add(new Line(area3Color,lineSpawnerX + random(-50,+50), -50));
            lines.add(new Line(area3Color,lineSpawnerX + random(-50,+50), -50));            
        }

    }
}

public void triangleSpawner(){

    if(triangleSpawnerDirection == 0 && triangleSpawnerX > 0){
        triangleSpawnerX -= 3;
        if(triangleSpawnerX <= 0){
            triangleSpawnerDirection = 1;
        }
    }

    if(triangleSpawnerDirection == 1 && triangleSpawnerX < gameWindowWidth){
        triangleSpawnerX += 3;
        if(triangleSpawnerX >= gameWindowWidth){
            triangleSpawnerDirection = 0;
        }
    }

    if(timer % 10 == 0){
        if(returnArea(triangleSpawnerX) == 0){
            triangles.add(new Triangle(area1Color, triangleSpawnerX + random(-50,+50), -50));
            triangles.add(new Triangle(area1Color,triangleSpawnerX + random(-50,+50), -50));
            triangles.add(new Triangle(area1Color,triangleSpawnerX + random(-50,+50), -50));
        }else if(returnArea(triangleSpawnerX) == 1){
            triangles.add(new Triangle(area2Color,triangleSpawnerX + random(-50,+50), -50));
            triangles.add(new Triangle(area2Color,triangleSpawnerX + random(-50,+50), -50));
            triangles.add(new Triangle(area2Color,triangleSpawnerX + random(-50,+50), -50));
        }else if(returnArea(triangleSpawnerX) == 2){
            triangles.add(new Triangle(area3Color,triangleSpawnerX+ random(-50,+50), -50));
            triangles.add(new Triangle(area3Color,triangleSpawnerX + random(-50,+50), -50));
            triangles.add(new Triangle(area3Color,triangleSpawnerX+ random(-50,+50), -50));
        }
        
    }
}

public boolean circleCircleCollision(float c1x, float c1y, float c1r, float c2x, float c2y, float c2r){
    float dx = c1x - c2x;
    float dy = c1y - c2y;
    float dr = sqrt( sq(dx) + sq(dy) );

    if(dr <= c1r + c2r){
        return true;
    }

    return false;
}

public boolean circleRectangleCollision(float cx, float cy, float cr, float rx, float ry, float rw, float rh){
    //Test kenarları
    float testX = cx;
    float testY = cy;
    

    //Çembere en yakın kenarları buluyoruz

    if(cx < rx){ // Sol kenar
        testX = rx;
    }else if(cx > rx + rw){ // Sağ kenar
        testX = rx + rw;
    }

    if(cy < ry){ // Üst kenar
        testY = ry;
    }else if(cy > ry + rh){ // Alt kenar
        testY = ry + rh;
    }

    //En yakın kenara uzaklık hesaplıyoruz

    float distX = cx - testX;
    float distY = cy - testY;
    float distance = sqrt ( sq(distX) + sq(distY) ); 

    if(distance <= cr){
        return true;
    }

    return false;
}

public boolean circlePointCollision(float px, float py, float cx, float cy, float cr){
    // Daire merkezi ve nokta arasındaki uzaklık
    float distX = px - cx;
    float distY = py - cy;
    float distance = sqrt( sq(distX) + sq(distY) );

    if(distance <= cr){
        return true;
    }

    else return false;
}

public boolean linePointCollision(float x1, float y1, float x2, float y2, float px, float py){
    //Noktanın doğrunun 2 ucuna olan uzaklığı
    float d1 = dist(px,py,x1,y1);
    float d2 = dist(px,py,x2,y2);

    //Doğrunun uzaklığı
    float lineLen = dist(x1,y1,x2,y2);

    //float için hassaslık payı veriyoruz
    float buffer = 0.1f;

    if(d1+d2 >= lineLen - buffer && d1+d2 <= lineLen + buffer){
        return true;
    }

    else return false;
}

public boolean circleLineCollision(float x1, float y1, float x2, float y2, float cx, float cy, float cr){
    // doğrunun başlangıç ve bitiş noktaları dairenin içindeyse çarpışma vardır
    boolean inside1 = circlePointCollision(x1,y1,cx,cy,cr);
    boolean inside2 = circlePointCollision(x2,y2,cx,cy,cr);
    if(inside1 || inside2){
        return true;
    }

    //Doğrunun uzunluğu
    float distX = x1 - y1;
    float distY = y1 - y2;
    float len = sqrt( sq(distX) + sq(distY) );

    // Nokta ve daire merkezinin iç çarpımı
    float dot = (((cx - x1) * (x2 - x1)) + ((cy - y1) * (y2 - y1)) ) / pow(len,2);

    //Doğruya en yakın nokta
    float closestX = x1 + (dot * (x2-x1));
    float closestY = y1 + (dot * (y2-y1));

    //Nokta gerçekten doğru ile aynı doğrultuda mı?
    boolean onSegment = linePointCollision(x1,y1,x2,y2,closestX,closestY);
    //Aynı doğrultuda değilse çarpışma yoktur
    if(!onSegment){
        return false;
    }

    // En yakın noktanın çemberin merkezine uzaklığı
    distX = closestX - cx;
    distY = closestY - cy;
    float distance = sqrt( sq(distX) + sq(distY) );

    if(distance <= cr){
        return true;
    }

    return false;
}

public int returnArea(float px){
    if(px >= 0 && px < (gameWindowWidth/3) ){
        return 0;
    }else if(px >= (gameWindowWidth/3) && px < (gameWindowWidth/3) * 2){
        return 1;
    }else if(px > (gameWindowWidth/3) * 2 && px < gameWindowWidth){
        return 2;
    }

    return 0;
}

public void colorController(){
    if(spawnerTimer > (gameWindowHeight/3)){
        area1Color += 1;
        
        spawnerTimer = 0;
        area2Color += 1;
        area3Color += 1;

        if(area1Color >= 3){
            area1Color = 0;
        }
        if(area2Color >= 3){
            area2Color = 0;
        }
        if(area3Color >= 3){
            area3Color = 0;
        }
    }
}

public boolean polygonCircleCollision(PVector [] vertices, float cx, float cy, float cr){
    int next = 0;

    for(int current = 0; current < vertices.length; current++){
        
    next = current + 1;
    
        if(next == vertices.length){
            next = 0;
        }

    PVector vc = vertices[current];
    PVector vn = vertices[next];

    boolean collision = circleLineCollision(vc.x, vc.y, vn.x, vn.y, cx, cy, cr);

        if(collision){
            return true;
        }

    }

    return false;
}

public void drawBackground(){
    background(255);

    pushStyle();
        for(int i = 0; i <= gameWindowWidth; i += 16){
            for(int j = 0; j <= gameWindowHeight; j += 16){           
                noFill();
                stroke(0,0,0,20);
                rect(i,j,16,16);    
            }
        }
    popStyle();
}

public void drawCircles(){
    for(int i = 0; i < circles.size(); i++){
        circles.get(i).draw();

        //Karakterle çarpışma
        boolean hit = circleCircleCollision(circles.get(i).positionX, circles.get(i).positionY, circles.get(i).radius, player.positionX, player.positionY, player.radius);
        
        if(circles.get(i).positionY > gameWindowHeight+100){
            circles.remove(i);
        }

        if(circles.get(i).positionX < 0 || circles.get(i).positionX > gameWindowWidth){
            circles.remove(i);
        }
        
        if(hit){
            if(circles.get(i).colorType != player.colorType){
                player.radius -= 3;
        }



            
            if(circles.get(i).colorType == player.colorType){
                player.radius += 0.6f;
            }

            if(circles.get(i).colorType == 0){
                player.colorType = 0;
                player.fillColor = color(192, 57, 43, random(150,255));
            }else if(circles.get(i).colorType == 1){
                player.colorType = 1;
                player.fillColor = color(46, 204, 113, random(150,255));
            }else if(circles.get(i).colorType == 2){
                player.colorType = 2;
                player.fillColor = color(52, 152, 219, random(150,255));
            }
            circles.remove(i);
        }
    }
}

public void drawRectangles(){
    for(int i = 0; i < rectangles.size(); i++){
        rectangles.get(i).draw();

        boolean hit = circleRectangleCollision(player.positionX, player.positionY, player.radius, rectangles.get(i).positionX, rectangles.get(i).positionY, rectangles.get(i).sizeX, rectangles.get(i).sizeY);
        
        if(rectangles.get(i).positionY > gameWindowHeight+100){
            rectangles.remove(i);
        }

        if(rectangles.get(i).positionX < 0 || rectangles.get(i).positionX > gameWindowWidth){
            rectangles.remove(i);
        }

        if(hit){
            if(rectangles.get(i).colorType != player.colorType){
                player.radius -= 3;
            }

            if(rectangles.get(i).colorType == player.colorType){
                player.radius += 0.6f;
            }

            if(rectangles.get(i).colorType == 0){
                player.colorType = 0;
                player.fillColor = color(192, 57, 43, random(150,255));
            }else if(rectangles.get(i).colorType == 1){
                player.colorType = 1;
                player.fillColor = color(46, 204, 113, random(150,255));
            }else if(rectangles.get(i).colorType == 2){
                player.colorType = 2;
                player.fillColor = color(52, 152, 219, random(150,255));
            }
            rectangles.remove(i);
        }
    }
}

public void drawLines(){
    for(int i = 0; i < lines.size(); i++){
        lines.get(i).draw();

        boolean hit = circleLineCollision(lines.get(i).point1X, lines.get(i).point1Y, lines.get(i).point2X, lines.get(i).point2Y, player.positionX, player.positionY, player.radius);

        if(lines.get(i).positionY > gameWindowHeight+100){
            lines.remove(i);
        }

        if(lines.get(i).positionX < 0 || lines.get(i).positionX > gameWindowWidth){
            lines.remove(i);
        }
        if(hit){
            if(lines.get(i).colorType != player.colorType){
                player.radius -= 3;
        }

            
            if(lines.get(i).colorType == player.colorType){
                player.radius += 0.6f;
            }

            if(lines.get(i).colorType == 0){
                player.colorType = 0;
                player.fillColor = color(192, 57, 43, random(150,255));
            }else if(lines.get(i).colorType == 1){
                player.colorType = 1;
                player.fillColor = color(46, 204, 113, random(150,255));
            }else if(lines.get(i).colorType == 2){
                player.colorType = 2;
                player.fillColor = color(52, 152, 219, random(150,255));
            }
            lines.remove(i);
            
        }
    }
}

public void drawTriangles(){
    for(int i = 0; i < triangles.size(); i++){
        triangles.get(i).draw();

        boolean hit = polygonCircleCollision(triangles.get(i).vertices, player.positionX, player.positionY, player.radius);

        if(triangles.get(i).positionY > gameWindowHeight+100){
            triangles.remove(i);
        }

        if(triangles.get(i).positionX < 0 || triangles.get(i).positionX > gameWindowWidth){
            triangles.remove(i);
        }

        
        if(hit){
            
            if(triangles.get(i).colorType != player.colorType){
                player.radius -= 3;
            }

            
            if(triangles.get(i).colorType == player.colorType){
                player.radius += 0.6f;
            }

            if(triangles.get(i).colorType == 0){
                player.colorType = 0;
                player.fillColor = color(192, 57, 43, random(150,255));
            }else if(triangles.get(i).colorType == 1){
                player.colorType = 1;
                player.fillColor = color(46, 204, 113, random(150,255));
            }else if(triangles.get(i).colorType == 2){
                player.colorType = 2;
                player.fillColor = color(52, 152, 219, random(150,255));
            }
            
            triangles.remove(i);
            
        }
        
    }
}

public void drawGui(){
    fill(0);
    textSize(20);
    textAlign(LEFT,TOP);
    text("Skor:      " + timer/60, 12,8);

    textSize(17);
    textAlign(LEFT,BOTTOM);
    //text("Bak kardeşim gökten şekiller yağıyo değdiğin şeklin rengini\nalırsın eğer farklı renge değersen küçülürsün kendi rengini\nalırsan ve zamanla büyüyosun hayatta kalmaya çalış ve\nbana yorum yapıp fikir ver", 12, gameWindowHeight - 8);
}


//DOKUNMATİK FONKSİYONLARI

public void touchStarted(){
    if(player.movementType == "mobile"){
        touchX1 = mouseX;
        touchY1 = mouseY;
    }
}

public void touchMoved(){
    if(player.movementType == "mobile"){
          touchX2 = mouseX;
        touchY2 = mouseY;
        
        player.positionX += touchX2-touchX1;
        player.positionY += touchY2-touchY1;
        
        touchX1 = touchX2;
        touchY1 = touchY2;
    }
}

public void touchEnded(){
    if(player.movementType == "mobile"){
          touchX1 = mouseX;
        touchY1 = mouseY;
    }
}
class Circle{
    float positionX;
    float positionY;
    float radius;
    int colorType;
    int fillColor;
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

        speed = random(2.4f,2.7f);
    }

    public void draw(){
        movement();
        fill(fillColor);
        ellipse(positionX, positionY, radius * 2, radius * 2);
    }

    public void movement(){
        positionY += speed;
    }

}
class Line{
    float positionX;
    float positionY;
    float point1X;
    float point1Y;
    float point2X;
    float point2Y;
    int colorType;
    int fillColor;
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

        speed = random(2.4f,2.7f);

    }

    public void draw(){
        movement();

        pushStyle();
            stroke(fillColor);
            strokeWeight(6);
            line(point1X, point1Y, point2X, point2Y);
        popStyle();

    }

    public void movement(){
        positionY += speed;
        point1Y += speed;
        point2Y += speed;
    }

}
class Player{
    String type;
    float positionX;
    float positionY;
    float radius;
    int colorType;
    int fillColor;
    String movementType;

    Player(String type, float positionX, float positionY){
        this.type = type;
        this.positionX = positionX;
        this.positionY = positionY;
        radius = 60;
        colorType = 3;
        fillColor = color(0,0,0,180);
    }

    public void draw(){
        radius += 0.008f;

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

    public void movement(){
      if(movementType == "mouse"){
        positionX = mouseX;
        positionY = mouseY;
      }        

    }

}
class Rectangle{
    float positionX;
    float positionY;
    float sizeX;
    float sizeY;
    int colorType;
    int fillColor;
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
        
        speed = random(2.4f,2.7f);
    }

    public void draw(){
        movement();
        fill(fillColor);
        rect(positionX, positionY, sizeX, sizeY);
    }

    public void movement(){
        positionY += speed;
    }

}
class Triangle{
    float positionX;
    float positionY;
    int colorType;
    int fillColor;
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

        float area = 0.5f * abs( vertices[0].x * vertices[1].y + vertices[1].x * vertices[2].y + vertices[2].x * vertices[0].y - ( vertices[0].y * vertices[1].x + vertices[1].y * vertices[2].x + vertices[2].y * vertices[0].x ) );
        while(area < 150){
            vertices[0] = new PVector(random(positionX-20,positionX+20),random(positionY-20,positionY+20));
            vertices[1] = new PVector(random(positionX-20,positionX+20),random(positionY-20,positionY+20));
            vertices[2] = new PVector(random(positionX-20,positionX+20),random(positionY-20,positionY+20));
            area = 0.5f * abs( vertices[0].x * vertices[1].y + vertices[1].x * vertices[2].y + vertices[2].x * vertices[0].y - ( vertices[0].y * vertices[1].x + vertices[1].y * vertices[2].x + vertices[2].y * vertices[0].x ) );
        }


        if(colorType == 0){ //Red
            fillColor = color(192, 57, 43, random(150,255));
        }else if(colorType == 1){ //Green
            fillColor = color(46, 204, 113, random(150,255));
        }else if(colorType == 2){ //Blue
            fillColor = color(52, 152, 219, random(150,255));
        }
        
        speed = random(2.4f,2.7f);
    }

    public void draw(){
        movement();
        
        
        beginShape();
        fill(fillColor);
        for(PVector v: vertices){
            vertex(v.x, v.y);
        }
        endShape();
    }

    public void movement(){
        positionY += speed;
        vertices[0].add(0,speed);
        vertices[1].add(0,speed);
        vertices[2].add(0,speed);
    }

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "GeometryFall" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
