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
package org.xmind.core.internal.compatibility;

import java.io.IOException;

import org.xmind.core.CoreException;
import org.xmind.core.internal.dom.WorkbookImpl;
import org.xmind.core.io.IInputSource;
import org.xmind.core.util.IXMLLoader;

public abstract class FileFormat {

    protected IInputSource source;

    protected IXMLLoader loader;

    public FileFormat(IInputSource source, IXMLLoader loader) {
        super();
        this.source = source;
        this.loader = loader;
    }

    public abstract boolean identifies();

    public abstract WorkbookImpl load() throws CoreException, IOException;

}