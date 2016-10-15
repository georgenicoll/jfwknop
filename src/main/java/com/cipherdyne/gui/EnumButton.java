/* 
 * JFwknop is developed primarily by the people listed in the file 'AUTHORS'.
 * Copyright (C) 2016 JFwknop developers and contributors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.cipherdyne.gui;

/**
 * List of button available from the main window
 * 
 * @author Franck Joncourt
 */
public enum EnumButton {
    CIPHER_GENERATE_RIJNDAEL_KEY,
    CIPHER_REMOVE_RIJNDAEL_KEY,
    CIPHER_GENERATE_BASE64_RIJNDAEL_KEY,
    CIPHER_REMOVE_BASE64_RIJNDAEL_KEY,
    CIPHER_GENERATE_BASE64_GPG,
    CIPHER_SELECT_SIGNER_GPG_ID,
    CIPHER_SELECT_RECIPIENT_GPG_ID,
    CIPHER_BROWSE_GPG_HOMEDIR,
    CIPHER_GENERATE_HMAC_KEY,
    CIPHER_REMOVE_HMAC_KEY,
    CIPHER_GENERATE_BASE64_HMAC_KEY,
    CIPHER_REMOVE_BASE64_HMAC_KEY,

    GENERAL_BROWSE_FOR_IP;
}
