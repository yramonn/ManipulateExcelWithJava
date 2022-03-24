package com.sovos;

import com.sovos.Conexao;
import com.sovos.Cliente;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static final String fileName = "C://Users//Ramon.Silva//Downloads//excel//teste.xlsx";

    public static void main(String[] args) throws IOException {

        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;


        List<Cliente> listCliente = new LinkedList<Cliente>();


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheetCliente = workbook.createSheet("Clientes");


        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();

        int rownum = 0;

        try {
            Conexao c = new Conexao();
            conn = c.getNewConecction();
            stmt = conn.createStatement();


            if (stmt.execute("SELECT * FROM cliente")) {
                rs = stmt.getResultSet();
            }
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId_cliente(rs.getInt("id_cliente"));
                cl.setNome(rs.getString("nome"));
                cl.setCpf(rs.getString("Cpf"));
                cl.setDt_nasc(rs.getDate("dt_nasc"));
                cl.setTelefone(rs.getString("telefone"));
                listCliente.add(cl);
            }

            Row rowCab = sheetCliente.createRow(rownum++);
            int cellnumCab = 0;

            Cell cellId = rowCab.createCell(cellnumCab++);
            Cell cellNome = rowCab.createCell(cellnumCab++);
            Cell cellCpf = rowCab.createCell(cellnumCab++);
            Cell cellDtNasc = rowCab.createCell(cellnumCab++);
            Cell cellTelefone = rowCab.createCell(cellnumCab++);

            cellId.setCellValue("Id");
            cellNome.setCellValue("Nome");
            cellCpf.setCellValue("CPF");
            cellDtNasc.setCellValue("Data Nascimento");
            cellTelefone.setCellValue("Telefone");

            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

            for (Cliente cl : listCliente) {
                Row row = sheetCliente.createRow(rownum++);
                int cellnum = 0;

                cellId = row.createCell(cellnum++);
                cellNome = row.createCell(cellnum++);
                cellCpf = row.createCell(cellnum++);
                cellDtNasc = row.createCell(cellnum++);
                cellTelefone = row.createCell(cellnum++);
                cellId.setCellValue(cl.getId_cliente());

                cellNome.setCellValue(cl.getNome());
                cellCpf.setCellValue(cl.getCpf());
                cellDtNasc.setCellValue(cl.getDate_nasc());
                cellDtNasc.setCellStyle(cellStyle);
                cellTelefone.setCellValue(cl.getTelefone());
            }

            FileOutputStream out = new FileOutputStream(new File(Main.fileName));
            workbook.write(out);
            out.close();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na edição do arquivo");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {

                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {

                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) {

                }
            }
        }
    }
}
