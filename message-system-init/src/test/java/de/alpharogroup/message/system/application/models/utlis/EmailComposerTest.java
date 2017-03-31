/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.message.system.application.models.utlis;

import java.util.Locale;

import de.alpharogroup.message.system.application.models.InfoMessageModel;
import de.alpharogroup.message.system.application.models.utils.EmailComposer;
import de.alpharogroup.resourcebundle.locale.Locales;

import org.testng.annotations.Test;



public class EmailComposerTest {

	@Test(enabled=false)
	public void testCreateEmailMessageForForgottenPassword() {
		InfoMessageModel model = EmailComposer
				.createEmailMessageForForgottenPassword("xy@z.com", "z.com",
						"abc", "Albert Einstein", "a.e@gmail.com", "yyy",
						"http://www.xy.com/bla=fasel", Locales.GREEK);
		System.out.println(model.getMessageContentModel().getSubject());

	}

	@Test(enabled=true)
	public void testCreateEmailMessageForRecommendProfile() {
		InfoMessageModel model = EmailComposer.createEmailMessageForRecommendProfile(
				"xy@z.com", "z.com",
				"abc", "Albert Einstein", "a.e@gmail.com", "yyy",
				"http://www.xy.com/bla=3", "Bla", "http://senderProfileLink", "http://signupLink", Locale.GERMAN);
		System.out.println(model.getMessageContentModel().getSubject());
		System.out.println(model.getMessageContentModel().getContent());
	}
}