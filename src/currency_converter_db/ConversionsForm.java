package currency_converter_db;



/** Conversion History screen (NetBeans form: see ConversionsForm.form). Behaviour lives in CrudController. */
public class ConversionsForm extends javax.swing.JFrame {

    private final CrudController ctl;

    public ConversionsForm() {
        initComponents();
        getContentPane().setBackground(UI.BG);
        TableSpec spec = Specs.conversions();
        lblHeading.setText(spec.title);
        lblWho.setText(Session.username == null ? "" : Session.username + " (" + Session.role + ")");
        ctl = new CrudController(this, spec, tblData, lblStatusBar,
                new javax.swing.JComponent[]{txtConversionId, cmbUserId, cmbBaseCurrencyId, cmbTargetCurrencyId, txtAmount, txtConvertedAmount, txtRateUsed, txtConversionDate},
                new javax.swing.JLabel[]{lblConversionId, lblUserId, lblBaseCurrencyId, lblTargetCurrencyId, lblAmount, lblConvertedAmount, lblRateUsed, lblConversionDate});
        btnNew.setEnabled(ctl.canSave());
        btnSave.setEnabled(ctl.canSave());
        btnDelete.setEnabled(ctl.canDelete());
        pack();
        UI.center(this);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        lblWho = new javax.swing.JLabel();
        pnlFields = new javax.swing.JPanel();
        lblConversionId = new javax.swing.JLabel();
        txtConversionId = new javax.swing.JTextField();
        lblUserId = new javax.swing.JLabel();
        cmbUserId = new javax.swing.JComboBox<>();
        lblBaseCurrencyId = new javax.swing.JLabel();
        cmbBaseCurrencyId = new javax.swing.JComboBox<>();
        lblTargetCurrencyId = new javax.swing.JLabel();
        cmbTargetCurrencyId = new javax.swing.JComboBox<>();
        lblAmount = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        lblConvertedAmount = new javax.swing.JLabel();
        txtConvertedAmount = new javax.swing.JTextField();
        lblRateUsed = new javax.swing.JLabel();
        txtRateUsed = new javax.swing.JTextField();
        lblConversionDate = new javax.swing.JLabel();
        txtConversionDate = new javax.swing.JTextField();
        scrData = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPreview = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblStatusBar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conversion History");

        pnlHeader.setBackground(new java.awt.Color(0, 82, 147));

        lblHeading.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        lblHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblHeading.setText("Conversion History");

        lblWho.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblWho.setForeground(new java.awt.Color(200, 220, 240));
        lblWho.setText(" ");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHeading)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblWho)
                .addGap(20, 20, 20))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHeading)
                    .addComponent(lblWho))
                .addGap(12, 12, 12))
        );

        pnlFields.setBackground(new java.awt.Color(255, 255, 255));

        lblConversionId.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblConversionId.setText("ID");

        lblUserId.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblUserId.setText("User");

        lblBaseCurrencyId.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblBaseCurrencyId.setText("From");

        lblTargetCurrencyId.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblTargetCurrencyId.setText("To");

        lblAmount.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblAmount.setText("Amount");

        lblConvertedAmount.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblConvertedAmount.setText("Converted Amount");

        lblRateUsed.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblRateUsed.setText("Rate Used");

        lblConversionDate.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblConversionDate.setText("Date");

        javax.swing.GroupLayout pnlFieldsLayout = new javax.swing.GroupLayout(pnlFields);
        pnlFields.setLayout(pnlFieldsLayout);
        pnlFieldsLayout.setHorizontalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFieldsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConversionId)
                    .addComponent(lblBaseCurrencyId)
                    .addComponent(lblAmount)
                    .addComponent(lblRateUsed))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtConversionId)
                    .addComponent(cmbBaseCurrencyId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAmount)
                    .addComponent(txtRateUsed))
                .addGap(30, 30, 30)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserId)
                    .addComponent(lblTargetCurrencyId)
                    .addComponent(lblConvertedAmount)
                    .addComponent(lblConversionDate))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbUserId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTargetCurrencyId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtConvertedAmount)
                    .addComponent(txtConversionDate))
                .addGap(20, 20, 20))
        );
        pnlFieldsLayout.setVerticalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFieldsLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConversionId)
                    .addComponent(txtConversionId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserId)
                    .addComponent(cmbUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBaseCurrencyId)
                    .addComponent(cmbBaseCurrencyId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTargetCurrencyId)
                    .addComponent(cmbTargetCurrencyId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAmount)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConvertedAmount)
                    .addComponent(txtConvertedAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRateUsed)
                    .addComponent(txtRateUsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConversionDate)
                    .addComponent(txtConversionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        tblData.setRowHeight(24);
        scrData.setViewportView(tblData);

        btnNew.setBackground(new java.awt.Color(0, 130, 80));
        btnNew.setBorderPainted(false);
        btnNew.setFocusPainted(false);
        btnNew.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setOpaque(true);
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(0, 82, 147));
        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setOpaque(true);
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(180, 40, 40));
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setOpaque(true);
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnFind.setBackground(new java.awt.Color(90, 100, 110));
        btnFind.setBorderPainted(false);
        btnFind.setFocusPainted(false);
        btnFind.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnFind.setForeground(new java.awt.Color(255, 255, 255));
        btnFind.setOpaque(true);
        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(90, 100, 110));
        btnFirst.setBorderPainted(false);
        btnFirst.setFocusPainted(false);
        btnFirst.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setOpaque(true);
        btnFirst.setText("|<  First");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrevious.setBackground(new java.awt.Color(90, 100, 110));
        btnPrevious.setBorderPainted(false);
        btnPrevious.setFocusPainted(false);
        btnPrevious.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnPrevious.setForeground(new java.awt.Color(255, 255, 255));
        btnPrevious.setOpaque(true);
        btnPrevious.setText("<  Prev");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(90, 100, 110));
        btnNext.setBorderPainted(false);
        btnNext.setFocusPainted(false);
        btnNext.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setOpaque(true);
        btnNext.setText("Next  >");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(90, 100, 110));
        btnLast.setBorderPainted(false);
        btnLast.setFocusPainted(false);
        btnLast.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setOpaque(true);
        btnLast.setText("Last  >|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnPreview.setBackground(new java.awt.Color(0, 130, 80));
        btnPreview.setBorderPainted(false);
        btnPreview.setFocusPainted(false);
        btnPreview.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnPreview.setForeground(new java.awt.Color(255, 255, 255));
        btnPreview.setOpaque(true);
        btnPreview.setText("Preview PDF");
        btnPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviewActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(180, 40, 40));
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setOpaque(true);
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblStatusBar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblStatusBar.setForeground(new java.awt.Color(90, 100, 110));
        lblStatusBar.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scrData)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(scrData, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(lblStatusBar)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        ctl.newRecord();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        ctl.save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        ctl.delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        ctl.find();
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        ctl.first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        ctl.previous();
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        ctl.next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        ctl.last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviewActionPerformed
        ctl.preview();
    }//GEN-LAST:event_btnPreviewActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPreview;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<Item> cmbBaseCurrencyId;
    private javax.swing.JComboBox<Item> cmbTargetCurrencyId;
    private javax.swing.JComboBox<Item> cmbUserId;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblBaseCurrencyId;
    private javax.swing.JLabel lblConversionDate;
    private javax.swing.JLabel lblConversionId;
    private javax.swing.JLabel lblConvertedAmount;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblRateUsed;
    private javax.swing.JLabel lblStatusBar;
    private javax.swing.JLabel lblTargetCurrencyId;
    private javax.swing.JLabel lblUserId;
    private javax.swing.JLabel lblWho;
    private javax.swing.JPanel pnlFields;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JScrollPane scrData;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtConversionDate;
    private javax.swing.JTextField txtConversionId;
    private javax.swing.JTextField txtConvertedAmount;
    private javax.swing.JTextField txtRateUsed;
    // End of variables declaration//GEN-END:variables
}
