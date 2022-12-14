package stud.anna;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilesParsingTest {

    ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void pdfParseTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File  downloadedPdf = $("a[href = 'junit-user-guide-5.9.1.pdf']").download();
        PDF Content = new PDF(downloadedPdf);
        assertThat(Content.author).contains("Sam Brannen");

    }

/*    @Test --чет не работает
    void xlsParseTest() throws Exception {
        open("https://kadet17.uralschool.ru/sveden/employees");
        File  downloadedXls = $("a[href = 'https://kadet17.uralschool.ru/file/download?id=2622']").download();
        XLS Content = new XLS(downloadedXls);
        System.out.println();

    }*/

    @Test
    void xlsParseTest() throws Exception {

        try (InputStream resourceAsStream = cl.getResourceAsStream("Журнал по листам ожидания-2022-11-02T16-01-48.xlsx")) {
            XLS content = new XLS(resourceAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue()).contains("Журнал по листам ожидания от 02.11.2022");
        }

    }
}
