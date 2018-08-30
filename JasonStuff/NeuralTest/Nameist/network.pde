//For a neural network of sorts.
class NeuralNetwork{
  public float[] output;
  public int[] nodesc;
  public float[][] node;
  float[][][] connections;
  
  NeuralNetwork(int input,int[] layers,int output){
     this.output = new float[output];
     this.nodesc = new int[2+layers.length];
     this.nodesc[0] = input;
     for(int x = 0;x<layers.length;x++){
       this.nodesc[x+1] = layers[x];
     }
     this.nodesc[1+layers.length]=output;
     node=new float[nodesc.length][];
     for(int x = 0; x<node.length;x++){
       node[x] = new float[nodesc[x]];
     }
  }
  
  void MakeRandomConnections(float min, float max){
    connections = new float[nodesc.length-1][][];
    for(int i = 0;i<connections.length;i++){
      connections[i] = new float[nodesc[i+1]][];
      for(int j = 0;j<nodesc[i+1];j++){
        connections[i][j] = new float[nodesc[i]];
        for(int k = 0;k<nodesc[i];k++){
          connections[i][j][k]=random(min,max);
        }
      }
    }
  }
  
  void mutate(int num,float dist){
    for(int i = 0;i<num;i++){
      int k = (int)random(0,connections.length);
      int l = (int)random(0,connections[k].length);
      int m = (int)random(0,connections[k][l].length);
      if(random(0,1)>0.5){
      connections[k][l][m] +=random(-dist,dist);
    }else{
    connections[k][l][m] =random(-dist,dist);
    }
    }
  
  }
  
  void runThrough(){
    for(int i = 1;i<node.length;i++){
      for(int j = 0;j<node[i].length;j++){
        node[i][j]=0;
        for(int k = 0;k<node[i-1].length;k++){
          node[i][j]+=node[i-1][k]*connections[i-1][j][k];
        }
        node[i][j]=sigmoid(node[i][j]);
      }      
    }
    setOutput();
  }
  
  void setOutput(){
    output = node[node.length-1];
  }
  
  void draw(int x, int y, int dx, int dy,int siz,int ex){
    ellipseMode(CENTER);
    rectMode(CENTER);
    noStroke();
    for(int i = 0; i < nodesc.length;i++){
      fill(255,200,150);
      rect(x+i*(dx+siz),y,siz+ex,(nodesc[i]*(siz+dy)-dy)+ex);
    }
    for(int i = 0;i<connections.length;i++){
      for(int j = 0;j<nodesc[i+1];j++){
        for(int k = 0;k<nodesc[i];k++){
          int r =0;
          if(connections[i][j][k]<0){
            r=255;
          }
          strokeWeight(abs(connections[i][j][k]));
          stroke(r,0,0,1+abs(connections[i][j][k]*255));
          line(x+(i+1)*(dx+siz),y+j*(dy+siz)-((dy+siz)*nodesc[i+1]-dy-siz)/2,x+(i)*(dx+siz),y+k*(dy+siz)-((dy+siz)*nodesc[i]-dy-siz)/2);
        }
      }
    }
    noStroke();
    for(int i = 0; i < nodesc.length;i++){
      for(int j = 0; j < nodesc[i];j++){
        fill(200,100,50);
        ellipse(x+i*(dx+siz),y+j*(dy+siz)-((dy+siz)*nodesc[i]-dy-siz)/2,siz,siz);
        fill(230,230,255);
        textSize(siz/2);
        textAlign(CENTER);
        String s = String.valueOf(node[i][j]);
        text(s,x+i*(dx+siz),y+j*(dy+siz)-((dy+siz)*nodesc[i]-dy-siz)/2,siz,siz);
      }
    }
  }
  
  void copyConnections(NeuralNetwork nn){
    for(int i = 0;i<connections.length;i++){
      for(int j = 0;j<connections[i].length;j++){
        for(int k = 0;k<connections[i][j].length;k++){
         connections[i][j][k]=nn.connections[i][j][k];
        }
      }
    }
  }
  
  float sigmoid(float x){
    return(float)(2/(1+Math.pow(2.71828,-x)))-1;
  }
  public float costF(float[] f){
    float c=0;
    runThrough();
    for(int i = 0;i<output.length;i++){
      c+=Math.pow(f[i]-output[i],2);
    }
    return(c);
  }
  void UpConnect(float[] f,float dx,float step){
    for(int i = 0;i<connections.length;i++){
      for(int j = 0;j<connections[i].length;j++){
        for(int k = 0;k<connections[i][j].length;k++){
           float Ocost = costF(f);
           connections[i][j][k]+=dx;
           float Ncost = costF(f);
           connections[i][j][k]-=dx;
           connections[i][j][k]+=(Ncost-Ocost)*step;
        }
      }
    }
  }
}
