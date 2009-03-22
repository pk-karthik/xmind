/* ******************************************************************************
 * Copyright (c) 2006-2008 XMind Ltd. and others.
 * 
 * This file is a part of XMind 3. XMind releases 3 and
 * above are dual-licensed under the Eclipse Public License (EPL),
 * which is available at http://www.eclipse.org/legal/epl-v10.html
 * and the GNU Lesser General Public License (LGPL), 
 * which is available at http://www.gnu.org/licenses/lgpl.html
 * See http://www.xmind.net/license.html for details.
 * 
 * Contributors:
 *     XMind Ltd. - initial API and implementation
 *******************************************************************************/
package org.xmind.core;

public interface IMeta extends IAdaptable, IWorkbookComponent {

    String SEP = "/"; //$NON-NLS-1$

    String AUTHOR = "Author"; //$NON-NLS-1$

    String DESCRIPTION = "Description"; //$NON-NLS-1$

    /**
     * 
     * @param keyPath
     * @return
     */
    String getValue(String keyPath);

    /**
     * 
     * @param key
     * @param value
     */
    void setValue(String keyPath, String value);

    /**
     * 
     * @param key
     * @return
     */
    IMetaData[] getMetaData(String key);

    /**
     * 
     * @param key
     * @return
     */
    IMetaData createMetaData(String key);

    /**
     * 
     * @param data
     */
    void addMetaData(IMetaData data);

    /**
     * 
     * @param data
     */
    void removeMetaData(IMetaData data);

}