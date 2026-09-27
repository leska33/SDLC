package com.example.proteinfit.view.ui;

import javax.swing.Icon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

public final class UiIcons {

    private static final float STROKE = 1.6f;
    private static final float PAD = 3.2f;

    private UiIcons() {
    }

    private abstract static class BaseIcon implements Icon {
        private final int size;
        private final Color color;

        BaseIcon(int size, Color color) {
            this.size = size;
            this.color = color;
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }

        @Override
        public final void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            AppTheme.enableQuality(g2);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(STROKE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            paint(g2, x, y, size);
            g2.dispose();
        }

        abstract void paint(Graphics2D g2, int x, int y, int size);
    }

    public static Icon dumbbell(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                float cy = y + size / 2f;
                float left = x + PAD;
                float right = x + size - PAD;
                float barH = size - PAD * 2 - 2;
                g2.draw(new Line2D.Float(left + 3, cy, right - 3, cy));
                g2.draw(new RoundRectangle2D.Float(left, cy - barH / 2f, 3.2f, barH, 1.5f, 1.5f));
                g2.draw(new RoundRectangle2D.Float(right - 3.2f, cy - barH / 2f, 3.2f, barH, 1.5f, 1.5f));
                g2.draw(new RoundRectangle2D.Float(left + 3.4f, cy - barH / 2f + 2, 2.4f, barH - 4, 1.5f, 1.5f));
                g2.draw(new RoundRectangle2D.Float(right - 5.8f, cy - barH / 2f + 2, 2.4f, barH - 4, 1.5f, 1.5f));
            }
        };
    }

    public static Icon scale(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                float left = x + PAD;
                float top = y + PAD;
                float w = size - PAD * 2;
                g2.draw(new Ellipse2D.Float(left + 1, top, w - 2, w - 5));
                g2.draw(new Line2D.Float(x + size / 2f, y + size - PAD - 3, x + size / 2f, y + size - PAD));
                g2.draw(new Line2D.Float(left + 1, y + size - PAD, left + w - 1, y + size - PAD));
                g2.draw(new Line2D.Float(x + size / 2f, top + 3, x + size / 2f + 3.5f, top + 7));
            }
        };
    }

    public static Icon ruler(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                float left = x + PAD;
                float top = y + PAD + 1;
                float w = size - PAD * 2;
                float h = size - PAD * 2 - 2;
                g2.draw(new RoundRectangle2D.Float(left, top, w, h, 2.5f, 2.5f));
                for (int i = 0; i < 4; i++) {
                    float sx = left + 3 + i * 3.2f;
                    g2.draw(new Line2D.Float(sx, top, sx, top + 4));
                }
            }
        };
    }

    public static Icon height(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                float cx = x + size / 2f;
                float top = y + PAD;
                float bottom = y + size - PAD;
                g2.draw(new Line2D.Float(cx, top, cx, bottom));
                g2.draw(new Line2D.Float(cx - 4, top, cx + 4, top));
                g2.draw(new Line2D.Float(cx - 4, bottom, cx + 4, bottom));
            }
        };
    }

    public static Icon gender(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                float d = size - PAD * 2 - 5;
                float left = x + (size - d) / 2f;
                float top = y + PAD;
                g2.draw(new Ellipse2D.Float(left, top, d, d));
                float cx = x + size / 2f;
                g2.draw(new Line2D.Float(cx, top + d, cx, y + size - PAD));
                g2.draw(new Line2D.Float(cx - 3.2f, y + size - PAD - 3, cx + 3.2f, y + size - PAD - 3));
            }
        };
    }

    public static Icon flame(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                Path2D path = new Path2D.Float();
                float cx = x + size / 2f;
                path.moveTo(cx, y + PAD);
                path.curveTo(x + size - PAD, y + size * 0.42f, x + size - PAD - 1, y + size - PAD, cx, y + size - PAD);
                path.curveTo(x + PAD + 1, y + size - PAD, x + PAD, y + size * 0.42f, cx, y + PAD);
                g2.draw(path);
                g2.draw(new Ellipse2D.Float(cx - 2.2f, y + size * 0.48f, 4.4f, 5.2f));
            }
        };
    }

    public static Icon check(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                g2.draw(new Ellipse2D.Float(x + PAD, y + PAD, size - PAD * 2, size - PAD * 2));
                g2.draw(new Line2D.Float(x + PAD + 3, y + size * 0.52f, x + size * 0.42f, y + size - PAD - 3));
                g2.draw(new Line2D.Float(x + size * 0.42f, y + size - PAD - 3, x + size - PAD - 2, y + PAD + 3));
            }
        };
    }

    public static Icon info(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                g2.draw(new Ellipse2D.Float(x + PAD, y + PAD, size - PAD * 2, size - PAD * 2));
                g2.fill(new Ellipse2D.Float(x + size / 2f - 1.3f, y + PAD + 3, 2.6f, 2.6f));
                g2.draw(new Line2D.Float(x + size / 2f, y + PAD + 8, x + size / 2f, y + size - PAD - 2));
            }
        };
    }

    public static Icon bulb(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                float d = size - PAD * 2 - 4;
                g2.draw(new Ellipse2D.Float(x + (size - d) / 2f, y + PAD, d, d));
                g2.draw(new RoundRectangle2D.Float(x + size / 2f - 3.2f, y + size - PAD - 3, 6.4f, 3.2f, 1.5f, 1.5f));
            }
        };
    }

    public static Icon plus(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                float cx = x + size / 2f;
                float cy = y + size / 2f;
                g2.draw(new Line2D.Float(cx, y + PAD + 1, cx, y + size - PAD - 1));
                g2.draw(new Line2D.Float(x + PAD + 1, cy, x + size - PAD - 1, cy));
            }
        };
    }

    public static Icon pencil(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                g2.draw(new Line2D.Float(x + PAD + 1, y + size - PAD - 1, x + size - PAD - 1, y + PAD + 1));
                g2.draw(new Line2D.Float(x + size - PAD - 4, y + PAD, x + size - PAD, y + PAD + 4));
                g2.draw(new Line2D.Float(x + PAD + 1, y + size - PAD - 1, x + PAD + 4, y + size - PAD));
            }
        };
    }

    public static Icon leaf(int size, Color color) {
        return new BaseIcon(size, color) {
            @Override
            void paint(Graphics2D g2, int x, int y, int size) {
                Path2D path = new Path2D.Float();
                path.moveTo(x + PAD, y + size - PAD);
                path.curveTo(x + PAD, y + PAD + 1, x + size - PAD, y + PAD, x + size - PAD - 1, y + size - PAD - 1);
                path.curveTo(x + size * 0.55f, y + size - PAD + 1, x + PAD + 2, y + size - PAD + 1, x + PAD, y + size - PAD);
                g2.draw(path);
                g2.draw(new Line2D.Float(x + PAD + 2, y + size - PAD - 1, x + size * 0.62f, y + PAD + 3));
            }
        };
    }
}
