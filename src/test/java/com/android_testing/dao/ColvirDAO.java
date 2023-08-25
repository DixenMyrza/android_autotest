package com.android_testing.dao;

import com.android_testing.config.DatabaseConfig;
import com.android_testing.config.PropertiesConfig;
import com.android_testing.models.DepositInfo;
import com.android_testing.models.TransferInfo;
import oracle.jdbc.OraclePreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ColvirDAO {

    @Autowired
    PropertiesConfig propertiesConfig;

    @Autowired
    DatabaseConfig databaseConfig;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String clientID;

    public String getDepositStatus(int amountSum) {

        clientID = propertiesConfig.getUser().getClientId();

        String depositStatus = new String();

        String query = "select * from ( " +
                "select o.code, bs.name from t_ord o, t_dea d, d_dea dd, g_cli g, g_clihst gh, t_procmem m, t_process p, t_bop_stat_std bs " +
                "where o.dep_id=d.dep_id and o.id=d.id " +
                "and d.dep_id=dd.dep_id and d.id=dd.id " +
                "and d.cli_dep_id=g.dep_id and d.cli_id=g.id " +
                "and g.dep_id=gh.dep_id and g.id=gh.id " +
                "and o.dep_id=m.dep_id and o.id=m.ord_id and m.mainfl=1 " +
                "and p.id=m.id " +
                "and bs.id=p.bop_id and bs.nord=p.nstat " +
                "and trunc(sysdate) between gh.fromdate and gh.todate and gh.arcfl<>1 " +
                "and gh.taxcode=? " +
                "and d.sdok=? " +
                "order by o.dimport desc " +
                ") c " +
                "where rownum=1";

        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = Objects.requireNonNull(databaseConfig
                    .jdbcTemplateWebApp()
                    .getDataSource())
                    .getConnection()
                    .prepareStatement(query);

            preparedStatement.setString(1, clientID);
            preparedStatement.setString(2, String.valueOf(amountSum));

            ResultSet results = preparedStatement.executeQuery();

            System.out.println(results);

            while (results.next()){
                DepositInfo depositInfo = new DepositInfo();
                depositInfo.setDepositStatus(results.getString("NAME"));
                depositStatus = depositInfo.getDepositStatus();
            }

            results.close();
            preparedStatement.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        System.out.println(amountSum);
        System.out.println(depositStatus);

        return depositStatus;
    }


    public String getTransferStatus(int amountSum, String receiverAcc, String receiverClientId){

        String transferStatus = new String();

        String query = "select bs.code as state_code, s.* from s_ordpay s, t_procmem m, t_process p, t_bop_stat_std bs " +
                "where s.dep_id=m.dep_id and s.id=m.ord_id and m.mainfl=1 " +
                "and p.id=m.id and bs.id=p.bop_id and bs.nord=p.nstat " +
                "and s.dop=trunc(sysdate) and s.rnn_cr=? " +
                "and substr(s.code_acr, 17, 4)=? " +
                "and s.amount=?";

        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = Objects.requireNonNull(databaseConfig
                            .jdbcTemplateWebApp()
                            .getDataSource())
                    .getConnection()
                    .prepareStatement(query);

            preparedStatement.setString(1, receiverClientId);
            preparedStatement.setString(2, receiverAcc);
            preparedStatement.setString(3, String.valueOf(amountSum));

            ResultSet results = preparedStatement.executeQuery();

            System.out.println(results.toString());

            while (results.next()){
                TransferInfo transferInfo = new TransferInfo();
                transferInfo.setTransferStatus(results.getString("STATE_CODE"));
                transferStatus = transferInfo.getTransferStatus();

                transferInfo.setSenderAcc(results.getString("DEP_ID"));
            }

            results.close();
            preparedStatement.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        System.out.println(amountSum);
        System.out.println(transferStatus);

        return transferStatus;
    }
}