package currency_converter_db;



/** Error Log screen (NetBeans form: see ErrorLogForm.form). Behaviour lives in CrudController. */
public class ErrorLogForm extends javax.swing.JFrame {

    private final CrudController ctl;

    public ErrorLogForm() {
        initComponents();
        getContentPane().setBackground(UI.BG);
        TableSpec spec = Specs.errorLog();
        lblHeading.setText(spec.title);
        lblWho.setText(Session.username == null ? "" : Session.username + " (" + Session.role + ")");
        ctl = new CrudController(this, spec, tblData, lblStatusBar,
                new javax.swing.JComponent[]{txtErrorId, cmbUserId, txtErrorMessage, txaStackTrace, txtOccurredAt},
                new javax.swing.JLabel[]{lblErrorId, lblUserId, lblErrorMessage, lblStackTrace, lblOccurredAt});
        btnNew.setEnabled(ctl.canSave());
        btnSave.setEnabled(ctl.canSave());
        btnDelete.setEnabled(ctl.canDelete());
        pack();
        UI.center(this);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        lblWho = new javax.swing.JLabel();
        pnlFields = new javax.swing.JPanel();
        lblErrorId = new javax.swing.JLabel();
        txtErrorId = new javax.swing.JTextField();
        lblUserId = new javax.swing.JLabel();
        cmbUserId = new javax.swing.JComboBox<>();
        lblErrorMessage = new javax.swing.JLabel();
        txtErrorMessage = new javax.swing.JTextField();
        lblStackTrace = new javax.swing.JLabel();
        scrTxaStackTrace = new javax.swing.JScrollPane();
        txaStackTrace = new javax.swing.JTextArea();
        lblOccurredAt = new javax.swing.JLabel();
        txtOccurredAt = new javax.swing.JTextField();
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
        setTitle("Error Log");

        pnlHeader.setBackground(new java.awt.Color(0, 82, 147));
        lblHeading.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblHeading.setText("Error Log");
        lblHeading.setForeground(new java.awt.Color(255, 255, 255));

        lblWho.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblWho.setText(" ");
        lblWho.setForeground(new java.awt.Color(200, 220, 240));

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
        lblErrorId.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblErrorId.setText("ID");

        lblUserId.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblUserId.setText("User");

        lblErrorMessage.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblErrorMessage.setText("Error Message");

        lblStackTrace.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblStackTrace.setText("Stack Trace");

        txaStackTrace.setColumns(20);
        txaStackTrace.setRows(3);
        txaStackTrace.setLineWrap(true);

        scrTxaStackTrace.setViewportView(txaStackTrace);

        lblOccurredAt.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblOccurredAt.setText("Occurred At");

        javax.swing.GroupLayout pnlFieldsLayout = new javax.swing.GroupLayout(pnlFields);
        pnlFields.setLayout(pnlFieldsLayout);
        pnlFieldsLayout.setHorizontalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFieldsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErrorId)
                    .addComponent(lblErrorMessage)
                    .addComponent(lblStackTrace)
                    .addComponent(lblOccurredAt))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtErrorId, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtErrorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(scrTxaStackTrace, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtOccurredAt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserId))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbUserId, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        pnlFieldsLayout.setVerticalGroup(
            pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFieldsLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblErrorId)
                    .addComponent(txtErrorId)
                    .addComponent(lblUserId)
                    .addComponent(cmbUserId))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblErrorMessage)
                    .addComponent(txtErrorMessage))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStackTrace)
                    .addComponent(scrTxaStackTrace, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOccurredAt)
                    .addComponent(txtOccurredAt))
                .addGap(10, 10, 10)
                .addGap(2, 2, 2))
        );

        tblData.setRowHeight(24);

        scrData.setViewportView(tblData);

        btnNew.setBackground(new java.awt.Color(0, 130, 80));
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnNew.setText("New");
        btnNew.setBorderPainted(false);
        btnNew.setFocusPainted(false);
        btnNew.setOpaque(true);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(0, 82, 147));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnSave.setText("Save");
        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.setOpaque(true);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(180, 40, 40));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnDelete.setText("Delete");
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnFind.setBackground(new java.awt.Color(90, 100, 110));
        btnFind.setForeground(new java.awt.Color(255, 255, 255));
        btnFind.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnFind.setText("Find");
        btnFind.setBorderPainted(false);
        btnFind.setFocusPainted(false);
        btnFind.setOpaque(true);
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(90, 100, 110));
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnFirst.setText("|<  First");
        btnFirst.setBorderPainted(false);
        btnFirst.setFocusPainted(false);
        btnFirst.setOpaque(true);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrevious.setBackground(new java.awt.Color(90, 100, 110));
        btnPrevious.setForeground(new java.awt.Color(255, 255, 255));
        btnPrevious.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnPrevious.setText("<  Prev");
        btnPrevious.setBorderPainted(false);
        btnPrevious.setFocusPainted(false);
        btnPrevious.setOpaque(true);
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(90, 100, 110));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnNext.setText("Next  >");
        btnNext.setBorderPainted(false);
        btnNext.setFocusPainted(false);
        btnNext.setOpaque(true);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(90, 100, 110));
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnLast.setText("Last  >|");
        btnLast.setBorderPainted(false);
        btnLast.setFocusPainted(false);
        btnLast.setOpaque(true);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnPreview.setBackground(new java.awt.Color(0, 130, 80));
        btnPreview.setForeground(new java.awt.Color(255, 255, 255));
        btnPreview.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnPreview.setText("Preview PDF");
        btnPreview.setBorderPainted(false);
        btnPreview.setFocusPainted(false);
        btnPreview.setOpaque(true);
        btnPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviewActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(180, 40, 40));
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnExit.setText("Exit");
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setOpaque(true);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblStatusBar.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblStatusBar.setText(" ");
        lblStatusBar.setForeground(new java.awt.Color(90, 100, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
            .addComponent(pnlFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scrData, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
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
                .addComponent(pnlHeader)
                .addComponent(pnlFields)
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
    private javax.swing.JComboBox<Item> cmbUserId;
    private javax.swing.JLabel lblErrorId;
    private javax.swing.JLabel lblErrorMessage;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblOccurredAt;
    private javax.swing.JLabel lblStackTrace;
    private javax.swing.JLabel lblStatusBar;
    private javax.swing.JLabel lblUserId;
    private javax.swing.JLabel lblWho;
    private javax.swing.JPanel pnlFields;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JScrollPane scrData;
    private javax.swing.JScrollPane scrTxaStackTrace;
    private javax.swing.JTable tblData;
    private javax.swing.JTextArea txaStackTrace;
    private javax.swing.JTextField txtErrorId;
    private javax.swing.JTextField txtErrorMessage;
    private javax.swing.JTextField txtOccurredAt;
    // End of variables declaration//GEN-END:variables
}
