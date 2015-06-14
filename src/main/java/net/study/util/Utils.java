package net.study.util;

import net.study.domain.Reply;
import net.study.error.CannotReplyException;
import net.study.error.LastChildAlreadyExistsException;
import net.study.error.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/3/15 | 12:02 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Component
public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    /*
    Check Parent
     */
    public void checkParent(Reply parent, long parentId) throws NotFoundException, CannotReplyException {
        if(parent == null){
            throw new NotFoundException("Can not found parent : " + parentId);
        }

        int parentLevel = parent.getLevel();
        if(parentLevel == 1){
            throw new CannotReplyException("You can not reply in last lever : " + parent.getId());
        }
    }

    /*
    Get Search Minimum Seqeunce Number
     */
    public String getSearchMinSeqNum(Reply parent){
        String parentSeqNum = parent.getSequenceNumber();
        DecimalFormat decimalFormat = new DecimalFormat("000000000000");
        long parentSeqLongValue = Long.parseLong(parentSeqNum);
        long searchMinLongValue = 0;
        switch (parent.getLevel()){                                             // 0000009999 21
            case 0:
                searchMinLongValue = parentSeqLongValue / 100L * 100L;          // 0000009999 00
                break;
        }
        return decimalFormat.format(searchMinLongValue);
    }
    /*
    Get Search Minimum Seqeunce Number (Level 3)

    public String getSearchMinSeqNum(ReplyVO parent){
        String parentSeqNum = parent.getSequenceNumber();
        DecimalFormat decimalFormat = new DecimalFormat("0000000000000000");
        long parentSeqLongValue = Long.parseLong(parentSeqNum);
        long searchMinLongValue = 0;
        switch (parent.getLevel()){                                             // 0000009999 654321
            case 0:
                searchMinLongValue = parentSeqLongValue / 1000000L * 1000000L;  // 0000009999 000000
                break;
            case 1:
                searchMinLongValue = parentSeqLongValue / 10000L * 10000L;      // 0000009999 650000
                break;
            case 2:
                searchMinLongValue = parentSeqLongValue / 100L * 100L;          // 0000009999 654300
                break;
        }
        return decimalFormat.format(searchMinLongValue);
    }
    */

    /*
    Generate Sequence Number
     */
    public String getSequenceNumber(Reply parent, String lastChildSeq) throws LastChildAlreadyExistsException {
        long parentSeqLong = Long.parseLong(parent.getSequenceNumber());
        int parentLevel = parent.getLevel();

        long decUnit = 0;
        if(parentLevel == 0){
            decUnit = 1L;
        }

        String sequenceNumber = null;

        DecimalFormat decimalFormat = new DecimalFormat("000000000000");
        if(lastChildSeq == null){   // reply not exist
            sequenceNumber = decimalFormat.format(parentSeqLong - decUnit);
        } else {    // reply already exists
            // check last child reply
            String orderOfLastChildSeq = null;
            if(parentLevel == 0){
                orderOfLastChildSeq = lastChildSeq.substring(10, 12);       // 0000000000 00
                sequenceNumber = lastChildSeq;
            }
            if(orderOfLastChildSeq.equals("00")){
                throw new LastChildAlreadyExistsException("Last child already exists : " + lastChildSeq);
            }
            long seq = Long.parseLong(sequenceNumber) - decUnit;
            sequenceNumber = decimalFormat.format(seq);
        }
        return sequenceNumber;
    }

    /*
    Generate Sequence Number (Level 3)

    public String getSequenceNumber(ReplyVO parent, String lastChildSeq) throws LastChildAleadyExistsException {
        long parentSeqLong = Long.parseLong(parent.getSequenceNumber());
        int parentLevel = parent.getLevel();

        long decUnit = 0;
        if(parentLevel == 0){
            decUnit = 10000L;
        } else if(parentLevel == 1){
            decUnit = 100L;
        } else if(parentLevel ==2){
            decUnit = 1L;
        }

        String sequenceNumber = null;

        DecimalFormat decimalFormat = new DecimalFormat("0000000000000000");
        if(lastChildSeq == null){   // reply not exist
            sequenceNumber = decimalFormat.format(parentSeqLong - decUnit);
        } else {    // reply already exists
            // check last child reply
            String orderOfLastChildSeq = null;
            if(parentLevel == 0){
                orderOfLastChildSeq = lastChildSeq.substring(10, 12);       // 0000000000 00 0000
                sequenceNumber = lastChildSeq.substring(0, 12) + "9999";    // 000000000000 + 9999
            } else if(parentLevel == 1){
                orderOfLastChildSeq = lastChildSeq.substring(12, 14);       // 000000000000 00 00
                sequenceNumber = lastChildSeq.substring(0, 14) + "99";      // 00000000000000 + 99
            } else if(parentLevel == 2){
                orderOfLastChildSeq = lastChildSeq.substring(14, 16);       // 00000000000000 00
                sequenceNumber = lastChildSeq;
            }
            if(orderOfLastChildSeq.equals("00")){
                throw new LastChildAleadyExistsException("Last child already exists : " + lastChildSeq);
            }
            long seq = Long.parseLong(sequenceNumber) - decUnit;
            sequenceNumber = decimalFormat.format(seq);
        }
        return sequenceNumber;
    }
     */

    /*
    Generate random string by length
     */
    public String randomString(int length) {

        LOGGER.debug("Utils random string, length={}", length);

        if (length < 1) throw new IllegalArgumentException("length < 1: " + length);

        char[] symbols;
        char[] buf;

        StringBuilder tmp = new StringBuilder();
        Random random = new Random();

        for (char ch = '0'; ch <= '9'; ++ch) tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch) tmp.append(ch);
        symbols = tmp.toString().toCharArray();

        buf = new char[length];

        for (int idx = 0; idx < buf.length; ++idx) buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    /*
    Generate random hash by string
     */
    public String getRandomHash(String str) {

        LOGGER.debug("Utils random string, str={}", str);

        SecureRandom random = new SecureRandom();
        String randomString = new BigInteger(130, random).toString(32);

        LOGGER.debug("Utils random string, randomString={}", randomString);

        return new BCryptPasswordEncoder().encode(str + randomString);
    }
}