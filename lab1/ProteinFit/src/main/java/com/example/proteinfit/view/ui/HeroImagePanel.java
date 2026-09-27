package com.example.proteinfit.view.ui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;

public class HeroImagePanel extends JPanel {

    private final BufferedImage source;
    private final int preferredWidth;
    private final int preferredHeight;

    public HeroImagePanel(String resourcePath, int width, int height) {
        this.preferredWidth = width;
        this.preferredHeight = height;
        setOpaque(false);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(Math.min(260, width), Math.min(400, height)));

        BufferedImage loaded = null;
        URL url = getClass().getResource(resourcePath);
        if (url != null) {
            try {
                loaded = ImageIO.read(url);
            } catch (Exception ignored) {
            }
        }
        this.source = loaded;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (source == null) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        AppTheme.enableQuality(g2);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int padX = 8;
        int padY = 12;
        int availW = Math.max(1, getWidth() - padX * 2);
        int availH = Math.max(1, getHeight() - padY * 2);
        double scale = Math.min(
                (double) availW / source.getWidth(),
                (double) availH / source.getHeight()
        );
        int w = Math.max(1, (int) Math.round(source.getWidth() * scale));
        int h = Math.max(1, (int) Math.round(source.getHeight() * scale));
        int x = (getWidth() - w) / 2;
        int y = (getHeight() - h) / 2;
        g2.drawImage(source, x, y, w, h, null);
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(preferredWidth, preferredHeight);
    }
}
