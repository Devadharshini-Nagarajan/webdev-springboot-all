package neu.edu.mainapp;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BlobDeserializer extends JsonDeserializer<Blob> {
    @Override
    public SerialBlob deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        byte[] blobBytes = p.getBinaryValue();
        SerialBlob blobb = null;
        try {
        	blobb = new javax.sql.rowset.serial.SerialBlob(blobBytes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blobb;
    }
}


