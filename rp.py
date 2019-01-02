import csv
import re
import spacy
import sys
import json
from io import StringIO
from io import BytesIO
from importlib import reload
reload(sys)
import pandas as pd
#sys.setdefaultencoding('utf8')
#from cStringIO import StringIO
from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage
import os
import sys, getopt
import numpy as np
from bs4 import BeautifulSoup
import urllib.request
#from urllib import urlopen
#Function converting pdf to string
def convert(fname, pages=None):
    if not pages:
        pagenums = set()
    else:
        pagenums = set(pages)

    output = StringIO()
    manager = PDFResourceManager()
    converter = TextConverter(manager, output, laparams=LAParams())
    interpreter = PDFPageInterpreter(manager, converter)

    infile = open(r"C:\NSD\rnd\py\resume parsing efforts\resume-parser-master\resume.pdf", encoding="utf8", errors='ignore')
    for page in PDFPage.get_pages(infile, pagenums):
        interpreter.process_page(page)
    infile.close()
    converter.close()
    text = output.getvalue()
    output.close
    return text
	
def convert_pdf_to_txt2(path_to_file):
    rsrcmgr = PDFResourceManager()
    retstr = StringIO()
    codec = 'utf-8'
    laparams = LAParams()
    device = TextConverter(rsrcmgr, retstr, codec=codec, laparams=laparams)
    fp = open(path_to_file, 'rb')
    interpreter = PDFPageInterpreter(rsrcmgr, device)
    password = ""
    maxpages = 0
    caching = True
    pagenos=set()

    for page in PDFPage.get_pages(fp, pagenos, maxpages=maxpages, password=password,caching=caching, check_extractable=True):
        interpreter.process_page(page)

    text = retstr.getvalue()

    fp.close()
    device.close()
    retstr.close()
    return text
	
def convert_pdf_to_txt(pdf_obj):
    rsrcmgr = PDFResourceManager()
    retstr = StringIO()
    fp = BytesIO(pdf_obj)  #get a file-like binary object
    codec = 'utf-8'
    laparams = LAParams()
    device = TextConverter(rsrcmgr, retstr, codec=codec, laparams=laparams)
    interpreter = PDFPageInterpreter(rsrcmgr, device)
    password = ""
    maxpages = 0
    caching = True
    pagenos=set()
    for page in PDFPage.get_pages(fp, pagenos, maxpages=maxpages, password=password,caching=caching, check_extractable=True):
        interpreter.process_page(page)
    fp.close()
    device.close()
    stri = retstr.getvalue()
    retstr.close()
    print(stri)
#Function to extract names from the string using spacy
def extract_name(string):
    r1 = str(string)
    nlp = spacy.load('en_core_web_sm')
    doc = nlp(r1)
    for ent in doc.ents:
        if(ent.label_ == 'PERSON'):
            print(ent.text)
            break
#Function to extract Phone Numbers from string using regular expressions
def extract_phone_numbers(string):
    r = re.compile(r'(\d{3}[-\.\s]??\d{3}[-\.\s]??\d{4}|\(\d{3}\)\s*\d{3}[-\.\s]??\d{4}|\d{3}[-\.\s]??\d{4})')
    phone_numbers = r.findall(string)
    return [re.sub(r'\D', '', number) for number in phone_numbers]
#Function to extract Email address from a string using regular expressions
def extract_email_addresses(string):
    r = re.compile(r'[\w\.-]+@[\w\.-]+')
    return r.findall(string)
#Converting pdf to string
resume_string = convert_pdf_to_txt2("9826661CV.pdf")
#print(resume_string)
resume_string1 = resume_string
#Removing commas in the resume for an effecient check
resume_string = resume_string.replace(',',' ')
#Converting all the charachters in lower case
resume_string = resume_string.lower()
#Information Extraction Function
def extract_information(string):
    string.replace (" ", "+")
    query = string
    soup = BeautifulSoup(urllib.request.urlopen("https://en.wikipedia.org/wiki/" + query), "html.parser")
    #creates soup and opens URL for Google. Begins search with site:wikipedia.com so only wikipedia
    #links show up. Uses html parser.
    for item in soup.find_all('div', attrs={'id' : "mw-content-text"}):
        print(item.find('p').get_text())
        print('\n')
with open('techatt.csv') as f:
    reader = csv.reader(f)
    your_listatt = list(reader)
with open('techskill.csv') as f:
    reader = csv.reader(f)
    your_list = list(reader)
with open('nontechnicalskills.csv') as f:
    reader = csv.reader(f)
    your_list1 = list(reader)
#Sets are used as it has a a constant time for lookup hence the overall the time for the total code will not exceed O(n)
s = set(your_list[0])
s1 = your_list
s2 = your_listatt
skillindex = []
skills = []
skillsatt = []
print('Name :')
extract_name(resume_string1)
print('\n')
print('Phone Number is :')
y = extract_phone_numbers(resume_string)
y1 = []
for i in range(len(y)):
    if(len(y[i])>9):
        y1.append(y[i])
print(y1)
print('\n')
print('Email id :')
print(extract_email_addresses(resume_string))
for word in resume_string.split(" "):
    if word in s:
        skills.append(word)
skills1 = list(set(skills))
print('\n')
print("Following are his/her Technical Skills :")
print('\n')
np_a1 = np.array(your_list)
for i in range(len(skills1)):
    item_index = np.where(np_a1==skills1[i])
    skillindex.append(item_index[1][0])

nlen = len(skillindex)
for i in range(nlen):
    print(skills1[i])
    print(s2[0][skillindex[i]])
    print('\n')

#Sets are used as it has a a constant time for lookup hence the overall the time for the total code will not exceed O(n)
s1 = set(your_list1[0])
nontechskills = []
for word in resume_string.split(" "):
    if word in s1:
        nontechskills.append(word)
nontechskills = set(nontechskills)
print('\n')

print("Following are his/her Non Technical Skills :")
list5 = list(nontechskills)
print('\n')
for i in range(len(list5)):
    print(list5[i])
print('\n \n')
