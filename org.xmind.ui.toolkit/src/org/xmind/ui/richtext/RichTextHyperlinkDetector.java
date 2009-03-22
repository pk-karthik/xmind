package org.xmind.ui.richtext;

import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.URLHyperlink;

public class RichTextHyperlinkDetector extends AbstractHyperlinkDetector {

    public IHyperlink[] detectHyperlinks(ITextViewer textViewer,
            IRegion region, boolean canShowMultipleHyperlinks) {

        if (region == null || textViewer == null)
            return null;
        IDocument document = textViewer.getDocument();
        if (document == null || !(document instanceof IRichDocument))
            return null;

        IRichDocument doc = (IRichDocument) document;
        List<Hyperlink> hyperlinks = getHyperlinksInRange(doc.getHyperlinks(),
                region);
        if (hyperlinks.isEmpty())
            return null;
        return parseHyperlinks(textViewer, hyperlinks);
    }

    protected IHyperlink[] parseHyperlinks(ITextViewer viewer,
            List<Hyperlink> hyperlinks) {
        int size = hyperlinks.size();
        IHyperlink[] list = new IHyperlink[size];
        for (int i = 0; i < size; i++) {
            Hyperlink hyperlink = hyperlinks.get(i);
            list[i] = parseHyperlink(viewer, hyperlink);
        }
        return list;
    }

    protected IHyperlink parseHyperlink(ITextViewer viewer, Hyperlink hyperlink) {
        return new URLHyperlink(new Region(hyperlink.start, hyperlink.length),
                hyperlink.href);
    }

    protected List<Hyperlink> getHyperlinksInRange(Hyperlink[] hyperlinks,
            IRegion region) {
        int start = region.getOffset();
        return RichTextUtils.getHyperlinksInRange(hyperlinks, start, start);
//        int end = start + region.getOffset();
//        return RichTextUtils.getHyperlinksInRange(hyperlinks, start, end);
    }
}
