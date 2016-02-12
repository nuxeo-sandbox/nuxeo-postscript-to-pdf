/**
 * 
 */

package org.nuxeo.postscript;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.blobholder.BlobHolder;
import org.nuxeo.ecm.core.convert.api.ConversionService;
import org.nuxeo.ecm.core.convert.cache.SimpleCachableBlobHolder;
import org.nuxeo.runtime.api.Framework;

/**
 * @author mgena
 */
@Operation(id=PostScriptToPDFConverter.ID, category=Constants.CAT_CONVERSION, label="Convert PostScript to PDF", description="")
public class PostScriptToPDFConverter {

    public static final String ID = "PostScriptToPDFConverter";

    @OperationMethod
    public Blob run(Blob input) {
    	ConversionService conversionService = Framework.getService(ConversionService.class);

    	List<Blob> blobs = new ArrayList<Blob>();
        blobs.add(input);
        BlobHolder blobHolder =  new SimpleCachableBlobHolder(blobs);

		Map<String, Serializable> params = new HashMap<String, Serializable>();
		params.put("targetFileName", input.getFilename().replace(".ps", ".pdf"));
		BlobHolder result = conversionService.convert("ps2pdf", blobHolder, params  );
    	return result.getBlob();
    }    

}
