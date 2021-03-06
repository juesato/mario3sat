import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import Main.grid;

public class GameFrame extends JFrame {
  GamePane gp;
  ArrayList<Sprite> sprites;
  ArrayList<Sprite> movingSprites;
  Sprite mario;
  int winWidth, winHeight;
  int winX, winY;
  int stageX, stageY;

  public GameFrame() {
    super("3-Sat as Mario");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    gp = new GamePane();
    setContentPane(gp);
    winWidth = 32 * 22;
    winHeight = 32 * 22;
    setSize(winWidth, winHeight);

    stageX = 32 * 40; // What the hell do these numbers mean I don't know
    stageY = 32 * 40;

    winX = 0;
    winY = 0;

    sprites = new ArrayList<Sprite>();
    movingSprites = new ArrayList<Sprite>();
    for (int i = 0; i < 40; i++) {
      for (int j = 0; j < 40; j++) {
        if (i == 0 || i == 39 || j == 0 || j == 39)
          sprites
              .add(new Sprite(i * 32.0 / stageX, j * 32.0 / stageY, stageX, stageY, Sprite.BLOCK));
      }
    }
    
    Gadget gadget = new Gadget("CROSSOVER_DR", false, 2, 2, 0, 0);
    sprites = gadget.getSprites();
    
//    MapGrid m = new MapGrid(Main.shittyGetter());
//    System.out.println("THERE ARE " + m.allSprites().size() + " SPRITES");
//    sprites = m.allSprites();

    sprites.add(new Sprite(2 * 32.0 / stageX, 36 * 32.0 / stageY, stageX, stageY,
        Sprite.QUESTION_BLOCK));

    mario = new Sprite(stageX, stageY, Sprite.MARIO);
    System.out.println(100.0 / stageX + " " + 100 / stageY);
//    mario.setX((int) (100.0 / stageX));
//    mario.setY((int) (100.0 / stageY));
    mario.setX(100); // Why does this not change where mario falls down?
    mario.setY(1);
    
    Sprite blockBelowMario = new Sprite(100.0 / stageX, 100.0 / stageY - .4, stageX, stageY, Sprite.BLOCK);
    Sprite a = new Sprite(100.0 / stageX, 100.0 / stageY + .4, stageX, stageY, Sprite.BLOCK);

//    mario = new Sprite();
    sprites.add(mario);
    movingSprites.add(mario);

    GameThread gt = new GameThread(sprites, movingSprites);
    gt.start();

    setVisible(true);
    gp.requestFocusInWindow();
  }

  public class GamePane extends JPanel implements KeyListener {
    public GamePane() {
      addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
      // try {
      // BufferedImage img = ImageIO.read(new File("brick.png"));
      // g.drawImage(img, 64,64,Color.white, null);
      // } catch (IOException e) {
      // e.printStackTrace();
      // }
      g.fillRect(0, 0, winWidth, winHeight);
      for (Sprite s : sprites) {
        // System.out.println("Drawing " + s);
        s.draw(g, winX, winY, winWidth, winHeight);
      }
    }


    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
      // System.out.println("key pressed");
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        mario.hspeed = -256.0 / stageX;
      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        mario.hspeed = 256.0 / stageX;
      } else if (e.getKeyCode() == KeyEvent.VK_UP) {
        if (!mario.airborne) {
          mario.airborne = true;
          mario.vspeed = -500.0 / stageY;
        }
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        if (mario.id == Sprite.SUPER_MARIO)
          mario.id = Sprite.CROUCH_MARIO;
      }
    }

    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        if (mario.hspeed < 0)
          mario.hspeed = 0;
      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        if (mario.hspeed > 0)
          mario.hspeed = 0;
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        if (mario.id == Sprite.CROUCH_MARIO)
          mario.id = Sprite.SUPER_MARIO;
      }
    }
  }

  public class GameThread extends Thread {
    boolean done = false;
    int freq = 60;
    long stepSize = 1000 / freq;
    ArrayList<Sprite> sprites;
    ArrayList<Sprite> movingSprites;

    public GameThread(ArrayList<Sprite> spritesIn, ArrayList<Sprite> movingSpritesIn) {
      this.sprites = spritesIn;
      this.movingSprites = movingSpritesIn;
    }

    @Override
    public void run() {

      while (!done) {
        long timestamp = System.currentTimeMillis();
        // Begin step computations
        for (Sprite sprite : (ArrayList<Sprite>) movingSprites.clone()) {
          sprite.x += sprite.hspeed / freq;
          for (Sprite sprite2 : (ArrayList<Sprite>) sprites.clone()) {
            if (sprite2 == sprite) continue;
            if (sprite.checkHorCollide(sprite2) && sprite.checkVertCollide(sprite2)) {
//              System.out.println("hor collision between " + sprite + " and " + sprite2);
              if (sprite.id == Sprite.KOOPA_SHELL) {
                if (sprite2.id == Sprite.BRICK) {
                  sprites.remove(sprite2);
                }
                if (sprite.hspeed > 0)
                  sprite.setX(sprite2.getPixelX() - sprite.width);
                else
                  sprite.setX(sprite2.getPixelX() + sprite2.width);
                sprite.hspeed *= -1;
              }
              if (sprite.id == Sprite.MARIO) {
                if (sprite.hspeed > 0)
                  sprite.setX(sprite2.getPixelX() - sprite.width);
                else
                  sprite.setX(sprite2.getPixelX() + sprite2.width);


                if (sprite2.id == Sprite.KOOPA) {

                  sprite2.id = Sprite.KOOPA_SHELL;
                  sprite2.hspeed = Sprite.SHELL_SPEED;
                  movingSprites.add(sprite2);
                }

                if (sprite2.id == Sprite.GOOMBA) {
                  sprites.remove(sprite2);

                }
                if (sprite2.id == Sprite.MUSHROOM) {
                  sprite.id = Sprite.SUPER_MARIO;
                  sprites.remove(sprite2);
                  sprite.update();
                }
              }
              if (sprite.id == Sprite.SUPER_MARIO) {
                if (sprite.hspeed > 0)
                  sprite.setX(sprite2.getPixelX() - sprite.width);
                else
                  sprite.setX(sprite2.getPixelX() + sprite2.width);
                if (sprite2.id == Sprite.KOOPA) {
                  sprite2.id = Sprite.KOOPA_SHELL;
                  sprite2.hspeed = Sprite.SHELL_SPEED;
                  movingSprites.add(sprite2);
                }
                if (sprite2.id == Sprite.GOOMBA) {
                  sprite.id = Sprite.MARIO;
                  sprite.update();

                }
              }
            }
          }

          sprite.vspeed += Sprite.gravity / stageY / freq;
          sprite.y += sprite.vspeed / freq;

          for (Sprite sprite2 : (ArrayList<Sprite>) sprites.clone()) {
            if (sprite2 == sprite) continue;
            if (sprite.checkVertCollide(sprite2) && sprite.checkHorCollide(sprite2)) {
              // System.out.println("vert collision between " + sprite + " and " + sprite2);
              if (sprite.id == Sprite.KOOPA_SHELL) {
                if (sprite.vspeed > 0)
                  sprite.setY(sprite2.getPixelY() - sprite.height);
                else
                  sprite.setY(sprite2.getPixelY() + sprite2.height);
                sprite.vspeed = 0;
              }
              if (sprite.id == Sprite.MARIO) {
                if (sprite.vspeed > 0) {
                  sprite.setY(sprite2.getPixelY() - sprite.height);
                  sprite.airborne = false;
                }
                else
                  sprite.setY(sprite2.getPixelY() + sprite2.height);
                if (sprite.vspeed > 0)
                  sprite.vspeed = 0;

                if (sprite2.id == Sprite.KOOPA) {
                  sprite2.id = Sprite.KOOPA_SHELL;
                  sprite2.hspeed = Sprite.SHELL_SPEED;
                  movingSprites.add(sprite2);
                  sprite.vspeed *= -1;
                }
                if (sprite2.id == Sprite.GOOMBA) {
                  sprites.remove(sprite2);
                }
                if (sprite2.id == Sprite.MUSHROOM) {
                  sprite.id = Sprite.SUPER_MARIO;
                  sprites.remove(sprite2);
                  sprite.update();
                }
                if (sprite2.id == Sprite.QUESTION_BLOCK) {
                  if (sprite.vspeed < 0) {      
                	Sprite mush=new Sprite(sprite2.x, sprite2.y, stageX, stageY, Sprite.MUSHROOM);
                	mush.setY(sprite2.getPixelY()-sprite2.width);
                    sprites.add(mush);
                    sprite2.id = Sprite.BLOCK;
                    sprite2.update();
                  }
                }


              }
              if (sprite.id == sprite.SUPER_MARIO) {
                if (sprite.vspeed > 0){
                  sprite.setY(sprite2.getPixelY() - sprite.height);
                  mario.airborne=false;
                }
                else
                  sprite.setY(sprite2.getPixelY() + sprite2.height);
                if (sprite.vspeed > 0)
                  sprite.vspeed = 0;
                if (sprite2.id == Sprite.KOOPA) {
                  sprite2.id = Sprite.KOOPA_SHELL;
                  sprite2.hspeed = Sprite.SHELL_SPEED;
                  movingSprites.add(sprite2);
                  sprite.vspeed *= -1;
                }
                if (sprite2.id == Sprite.GOOMBA) {
                  sprite.id = Sprite.MARIO;
                  sprite.update();
                  
                }
                if (sprite2.id == Sprite.QUESTION_BLOCK) {
                  if (sprite.vspeed < 0) {
                	Sprite mush=new Sprite(sprite2.x, sprite2.y, stageX, stageY, Sprite.MUSHROOM);
                  	mush.setY(sprite2.getPixelY()-sprite2.width);
                    sprites.add(mush);
                    sprite2.id=Sprite.BLOCK;
                    sprite2.update();
                  }
                }
                if (sprite2.id == Sprite.BRICK) {
                  if (sprite.vspeed < 0) {
                    sprites.remove(sprite2);
                    sprite.vspeed = 0;
                  }
                }

              }
              if (sprite.id == Sprite.CROUCH_MARIO) {
                if (sprite.vspeed > 0)
                  sprite.setY(sprite2.getPixelY() - sprite.height);
                else
                  sprite.setY(sprite2.getPixelY() + sprite2.height);

                if (sprite2.id == Sprite.KOOPA) {
                  sprites.add(new Sprite(sprite2.x, sprite2.y, stageX, stageY, Sprite.KOOPA_SHELL,
                      Sprite.SHELL_SPEED));
                  sprites.remove(sprite2);
                }
                if (sprite2.id == Sprite.GOOMBA) {
                  sprite.id=Sprite.MARIO;
                  sprite.update();
                }
                if (sprite2.id == Sprite.BRICK) {
                  if (sprite.vspeed > 0) {
                    sprites.remove(sprite2);
                    sprite.vspeed = 0;
                  }
                }
                if (sprite.vspeed > 0)
                  sprite.vspeed = 0;
              }
            }

          }
        }
        // End step computations
        winX = mario.getPixelX()-winWidth/2;
        winY = mario.getPixelY()-winHeight/2;
        gp.repaint();
        long left = timestamp + stepSize - System.currentTimeMillis();
        if (left > 0)
          delay(left);
      }
    }

    public void delay(long ms) {
      try {
        Thread.sleep(ms);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
