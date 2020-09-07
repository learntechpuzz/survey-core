package com.visiblestarsksa.survey.helpers;

import static com.visiblestarsksa.survey.util.CSVUtil.extractBoolean;
import static com.visiblestarsksa.survey.util.CSVUtil.extractDate;
import static com.visiblestarsksa.survey.util.CSVUtil.extractEnum;
import static com.visiblestarsksa.survey.util.CSVUtil.extractInt;
import static com.visiblestarsksa.survey.util.CSVUtil.extractLong;
import static com.visiblestarsksa.survey.util.CSVUtil.extractString;
import static com.visiblestarsksa.survey.util.CSVUtil.formatDate;

import com.visiblestarsksa.survey.domain.enums.EQuestionType;
import com.visiblestarsksa.survey.domain.enums.ESurveyUserStatus;
import com.visiblestarsksa.survey.exception.FormatException;
import com.visiblestarsksa.survey.model.Answer;
import com.visiblestarsksa.survey.model.Question;
import com.visiblestarsksa.survey.model.SurveyUser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SurveyCSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasAnswerRecord(CSVRecord record, int i) throws FormatException {
        return (record.isMapped("answer_en_" + i)
                && !StringUtils.isEmpty(extractString("answer_en_" + i, record))
                && record.isMapped("answer_ar_" + i)
                && !StringUtils.isEmpty(extractString("answer_ar_" + i, record)));
    }

    public static List<Question> csvToQuestions(InputStream is)
            throws IOException, FormatException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser =
                        new CSVParser(
                                fileReader,
                                CSVFormat.DEFAULT
                                        .withFirstRecordAsHeader()
                                        .withIgnoreHeaderCase()
                                        .withTrim()); ) {

            List<Question> questions = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                List<Answer> answers = new ArrayList<>();
                int i = 1;
                while (hasAnswerRecord(csvRecord, i)) {
                    answers.add(
                            Answer.builder()
                                    .answerEn(extractString("answer_en_" + i, csvRecord))
                                    .answerAr(extractString("answer_ar_" + i, csvRecord))
                                    .build());
                    i++;
                }
                questions.add(
                        Question.builder()
                                .stepNo(extractInt("step_no", csvRecord))
                                .questionEn(extractString("question_en", csvRecord))
                                .questionAr(extractString("question_ar", csvRecord))
                                .required(extractBoolean("required", csvRecord))
                                .type(extractEnum("type", csvRecord, EQuestionType.class, null))
                                .answers(answers)
                                .build());
            }
            return questions;
        }
    }

    public static List<SurveyUser> csvToSurveyUsers(InputStream is, Timestamp expireOn)
            throws IOException, FormatException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser =
                        new CSVParser(
                                fileReader,
                                CSVFormat.DEFAULT
                                        .withFirstRecordAsHeader()
                                        .withIgnoreHeaderCase()
                                        .withTrim()); ) {

            List<SurveyUser> surveyUsers = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                surveyUsers.add(
                        SurveyUser.builder()
                                .extractionDate(
                                        extractDate("extraction_date", csvRecord, "yyyy-MM-dd"))
                                .branchCode(extractLong("branch_code", csvRecord))
                                .maskParty(extractLong("mask_party", csvRecord))
                                .servedBy(extractString("served_by", csvRecord))
                                .transDesc(extractString("trans_desc", csvRecord))
                                .segment(extractString("segment", csvRecord))
                                .gender(extractString("gender", csvRecord))
                                .expireOn(expireOn)
                                .status(ESurveyUserStatus.NOTVIEWED)
                                .build());
            }

            return surveyUsers;
        }
    }

    public static ByteArrayInputStream surveyUsersToCSV(List<SurveyUser> surveyUsers) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format); ) {

            List<String> headers =
                    Arrays.asList(
                            "Branch_Code",
                            "Extraction_Date",
                            "Gender",
                            "Mask_Party",
                            "Segment",
                            "Served_By",
                            "Trans_Desc",
                            "Expire_On",
                            "Survey_URL");
            csvPrinter.printRecord(headers);
            for (SurveyUser surveyUser : surveyUsers) {
                List<String> data =
                        Arrays.asList(
                                String.valueOf(surveyUser.getBranchCode()),
                                formatDate(surveyUser.getExtractionDate(), "yyyy-MM-dd"),
                                surveyUser.getGender(),
                                String.valueOf(surveyUser.getMaskParty()),
                                surveyUser.getSegment(),
                                surveyUser.getServedBy(),
                                surveyUser.getTransDesc(),
                                formatDate(
                                        new Date(surveyUser.getExpireOn().getTime()),
                                        "yyyy-MM-dd hh:mm:ss"),
                                surveyUser.getSurveyUrl());

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
