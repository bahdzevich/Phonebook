package com.bogdevich.profile.controller.util.view;

import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CsvView extends AbstractCsvView {

    @SuppressWarnings(value = "unchecked")
    @Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"my-csv-file.csv\"");

        List<ProfileResponseDTO> profileResponseDTOList = (List<ProfileResponseDTO>) model.get("profiles");
        String[] header = {"id","email","name","lastname","skype","phone","room"};

        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(
                response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE
        )) {
            csvWriter.writeHeader(header);

            for(final ProfileResponseDTO profileResponseDTO : profileResponseDTOList){
                csvWriter.write(profileResponseDTO, header);
            }
        }
    }
}
