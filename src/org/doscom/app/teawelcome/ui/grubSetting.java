/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * grubSetting.java
 *
 * Created on 23 Mar 11, 14:30:55
 */

package org.doscom.app.teawelcome.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.doscom.app.teawelcome.appbuilder.io;
import org.doscom.app.teawelcome.appbuilder.runtime;

/**
 *
 * @author badak
 */
public class grubSetting extends javax.swing.JPanel {

    File file;
    String dir, timeouttxt, resolutiontxt, backgroundtxt, ishiddentxt, hiddentimeouttxt, isplymouthtxt,isrecoverytxt,ismemtesttxt;
    JFileChooser chooser=new JFileChooser();
    io output=new io();
    runtime run=new runtime();
    final String CONFIGURATION="grubManager.cfg";
    Properties p=new Properties();
    /** Creates new form grubSetting */
    public grubSetting() {
        initComponents();

        dir=System.getProperty("user.dir");
        FileNameExtensionFilter extensionFilter=new FileNameExtensionFilter("png", "png");
        chooser.addChoosableFileFilter(extensionFilter);

        loadConfig();
    }

    private void generate(){
        resolutiontxt="GRUB_GFXMODE="+resolution.getSelectedItem().toString();
        if(!timeout.getText().matches("")){
            timeouttxt="GRUB_TIMEOUT="+timeout.getText();
        }else{
            timeouttxt="GRUB_TIMEOUT=10";
        }
        if(!background.getText().matches("")){
            backgroundtxt="GRUB_BACKGROUND="+background.getText();
        }else{
            backgroundtxt="#GRUB_BACKGROUND=";
        }
        if(isHidden.isSelected()){
            ishiddentxt="GRUB_HIDDEN_TIMEOUT_QUIET=true";
            hiddentimeouttxt="#GRUB_HIDDEN_TIMEOUT=0";
        }else{
            ishiddentxt="GRUB_HIDDEN_TIMEOUT_QUIET=false";
            hiddentimeouttxt="GRUB_HIDDEN_TIMEOUT="+hiddenTimeout.getText();
        }
        if(isPlymouth.isSelected()){
            isplymouthtxt="GRUB_CMDLINE_LINUX_DEFAULT=\"quiet splash\"";
        }else{
            isplymouthtxt="#GRUB_CMDLINE_LINUX_DEFAULT=\"quiet splash\"";
        }
        if(isRecovery.isSelected()){
            isrecoverytxt="#GRUB_DISABLE_LINUX_RECOVERY=\"true\"";
        }else{
            isrecoverytxt="GRUB_DISABLE_LINUX_RECOVERY=\"true\"";
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("grub"));
            bw.write("# If you change this file, run 'update-grub' afterwards to update"
                    + "\n#/boot/grub/grub.cfg.\n");bw.newLine();
            bw.write("#Generated by TeaLinuxOS GRUB Manager");bw.newLine();
            bw.write("#created by masasdani\n\n");bw.newLine();
            bw.write("GRUB_DEFAULT=0");bw.newLine();
            bw.write(timeouttxt);bw.newLine();
            bw.write(hiddentimeouttxt);bw.newLine();
            bw.write(ishiddentxt);bw.newLine();
            bw.write("GRUB_DISTRIBUTOR=`lsb_release -i -s 2> /dev/null || echo Debian`");bw.newLine();
            bw.write(isplymouthtxt);bw.newLine();
            bw.write("GRUB_CMDLINE_LINUX=\"\"");bw.newLine();
            bw.write(resolutiontxt);bw.newLine();
            bw.write(backgroundtxt);bw.newLine();
            bw.write(isrecoverytxt);bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void saveConfig(){
        p.setProperty("timeout", timeout.getText());
        p.setProperty("resolution", ""+resolution.getSelectedIndex());
        p.setProperty("background", background.getText());
        if(isHidden.isSelected()) p.setProperty("ishidden", "true");
        else p.setProperty("ishidden", "false");
        p.setProperty("hiddentimeout", hiddenTimeout.getText());
        if(isPlymouth.isSelected()) p.setProperty("isplymouth", "true");
        else p.setProperty("isplymouth", "false");
        if(isRecovery.isSelected()) p.setProperty("isrecovery", "true");
        else p.setProperty("isrecovery", "false");
        if(isMemtest.isSelected()) p.setProperty("ismemtest", "true");
        else p.setProperty("ismemtest", "false");
        saveProperties(p, CONFIGURATION);
    }

    private void saveProperties(Properties properties, String file){
        try{
            FileOutputStream stream=new FileOutputStream(file);
            properties.store(stream, "Configuration File, do not edit\n@author masasdani\n\n");
            stream.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


    private Properties loadProperties(String file){
        Properties properties=new Properties();
        try{
            FileInputStream stream=new FileInputStream(file);
            properties.load(stream);
            stream.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return properties;
    }

    private void loadConfig(){
        File f=new File(CONFIGURATION);
        if(f.exists()){
            p=loadProperties(CONFIGURATION);
            timeout.setText(p.getProperty("timeout"));
            resolution.setSelectedIndex(Integer.parseInt(p.getProperty("resolution")));
            background.setText(p.getProperty("background"));
            if(p.getProperty("ishidden").matches("true")){
                isHidden.setSelected(true);
            }else{
                isHidden.setSelected(false);
            }
            hiddenTimeout.setText(p.getProperty("hiddentimeout"));
            if(p.getProperty("isplymouth").matches("true")){
                isPlymouth.setSelected(true);
            }else{
                isPlymouth.setSelected(false);
            }
            if(p.getProperty("isrecovery").matches("true")){
            isRecovery.setSelected(true);
            }else{
                isRecovery.setSelected(false);
            }
            if(p.getProperty("ismemtest").matches("true")){
                isMemtest.setSelected(true);
            }else{
                isMemtest.setSelected(false);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        timeout = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        resolution = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        background = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        hiddenTimeout = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        isHidden = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        isPlymouth = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        isRecovery = new javax.swing.JCheckBox();
        isMemtest = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 48));
        jLabel1.setText("TeaLinux OS GRUB Manager");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabel2.setForeground(java.awt.Color.black);
        jLabel2.setText("Grub TimeOut");

        timeout.setText("10");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabel3.setForeground(java.awt.Color.black);
        jLabel3.setText("Grub Resolution");

        resolution.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "640x480", "800x600", "1024x768", "1280x800" }));
        resolution.setLightWeightPopupEnabled(false);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabel4.setForeground(java.awt.Color.black);
        jLabel4.setText("Grub Background");

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        hiddenTimeout.setText("0");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabel9.setForeground(java.awt.Color.black);
        jLabel9.setText("Hidden Timeout");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabel5.setForeground(java.awt.Color.black);
        jLabel5.setText("Grub Hidden Quiet");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabel6.setForeground(java.awt.Color.black);
        jLabel6.setText("Enable Plymouth");

        isPlymouth.setSelected(true);

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabel7.setForeground(java.awt.Color.black);
        jLabel7.setText("Enable Recovery Options");

        isRecovery.setSelected(true);

        isMemtest.setSelected(true);

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabel8.setForeground(java.awt.Color.black);
        jLabel8.setText("Enable Memtest Options");

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 24));
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(isMemtest)
                    .addComponent(isRecovery)
                    .addComponent(isPlymouth)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(isHidden)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hiddenTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(timeout, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resolution, 0, 194, Short.MAX_VALUE)
                            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(timeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(resolution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(isHidden)
                    .addComponent(jLabel9)
                    .addComponent(hiddenTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(isPlymouth))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(isRecovery))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(isMemtest))
                .addGap(37, 37, 37)
                .addComponent(jButton2)
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int path=chooser.showOpenDialog(this);
        if(path==JFileChooser.APPROVE_OPTION){
            file=chooser.getSelectedFile();
            background.setText(file.getAbsolutePath());
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        saveConfig();
        generate();
        output.action();
        if(!isMemtest.isSelected()){
                run.memtesOff();
        }else{
                run.memtesOn();
        }
        run.bash();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField background;
    private javax.swing.JTextField hiddenTimeout;
    private javax.swing.JCheckBox isHidden;
    private javax.swing.JCheckBox isMemtest;
    private javax.swing.JCheckBox isPlymouth;
    private javax.swing.JCheckBox isRecovery;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox resolution;
    private javax.swing.JTextField timeout;
    // End of variables declaration//GEN-END:variables

}
