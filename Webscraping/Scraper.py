import time
import requests
import sys
#import lxml
import html5lib
from random import randint
#from urllib.request import urlopen
from bs4 import BeautifulSoup as soup

headers = {"User-Agent": "Web scraping program for project at Rutgers University. Contact me at cory551043@gmail.com"}

startURL = 'https://ndb.nal.usda.gov/ndb/search/list?maxsteps=6&format=&count=&max=50&sort=ndb&fgcd=&manu=&lfacet=&qlookup=&ds=&qt=&qp=&qa=&qn=&q=&ing=&offset=0&order=asc'
#startURL = input("Enter starting URL: ")
endPage = input("Enter page to end scrape (inclusive): ")

def getLinks(urlToScrape):
#Given a starting URL, will return a list of all the URLs of foods, the next page to visit, and the current page num
	pageSoup = getSoup(urlToScrape)
	
	foodsOnPage = pageSoup.findAll("tr",{"style":"line-height: 1.2em;"})
		
		#NEXT LINK FOUND HERE#
	nextButton = pageSoup.find("a",{"class":"nextLink"})
	
	if bool(nextButton):
		nextButton = nextButton['href']
		fullNextLink = 'https://ndb.nal.usda.gov' + nextButton
	else:
		fullNextLink = None

		#CURRENT PAGE NUM FOUND HERE#
	pageNum = pageSoup.find("span",{"class":"currentStep"})

	if bool(pageNum):
		pageNum = pageSoup.find("span",{"class":"currentStep"}).text
	else:
		pageNum = None

	urlList = [] #where we will store all links of foods on page
	
	#Our loop to retrieve all entry links
	for currEntry in foodsOnPage:
		temp = currEntry.find('a')
		temp = temp['href'] #this contains the link of current food w/o the prefix
		fullLink = 'https://ndb.nal.usda.gov' + temp
		urlList.append(fullLink)

	return urlList, fullNextLink, pageNum

def scrapeLinks(urlList):
#Traverses list of URLs, and retieves data wanted
	for link in range(len(urlList)):
		pageSoup = getSoup(urlList[link])

		print("\tFood #" + str(link+1) + " is being scraped...") 
			
			#Get product name
		productName = pageSoup.find("div",{"id":"view-name"}).text.strip()
		productName = productName.split(",")
		del productName[0]
		productName = ''.join(productName).strip()
			
			#Gets table which we need for Cal, Fat, Protein, etc.
		table = pageSoup.find("tbody")
		rows = table.findAll("tr") #Finds all rows in table

		helpArr = parseRows(rows)

		calIndex = helpArr[0]
		proIndex = helpArr[1]
		fatIndex = helpArr[2]
		carbIndex = helpArr[3]
		fibIndex = helpArr[4]
		sugIndex = helpArr[5]
		
		#For testing row numbers	
		#print(rows)
		#sys.exit(1)

			#Get Calories per 100grams
		if helpArr[0] != None:
			energyRow = rows[calIndex]
			calCol = energyRow.findChildren('td')
			calories = calCol[3].text.strip()
		else:
			calories = ''

		#print(calories)
		#sys.exit(1)

			#Get Protein
		if helpArr[1] != None:
			proRow = rows[proIndex]
			proCol = proRow.findChildren('td')
			protein = proCol[3].text.strip()
		else:
			protein = ''

			#Get Fat
		if helpArr[2] != None:
			fatRow = rows[fatIndex]
			fatCol = fatRow.findChildren('td')
			fat = fatCol[3].text.strip()
		else:
			fat = ''

			#Get Carbs
		if helpArr[3] != None:
			carbRow = rows[carbIndex]
			carbCol = carbRow.findChildren('td')
			carbs = carbCol[3].text.strip()
		else:
			carbs = ''
			
			#Get Fiber
		if helpArr[4] != None:
			fibRow = rows[fibIndex]
			fibCol = fibRow.findChildren('td')
			fiber = fibCol[3].text.strip()
		else:
			fiber = ''

			#Get Sugar
		if helpArr[5] != None:
			sugRow = rows[sugIndex]
			sugCol = sugRow.findChildren('td')
			sugar = sugCol[3].text.strip()
		else:
			sugar = ''

			#Get Ingredients
		hasStrong = pageSoup.findAll('strong') #All pages with ingredients have a strong tag

		if (len(hasStrong)) != 0:
			divList = pageSoup.findAll('div',{"class":"col-md-12"})
			sep = '\n'
			ing = divList[5].text.split(sep)[1].strip().replace(',','|')
		else:
			ing = "N/A"

			#Print Results
		print("\t\tName: " + productName)
		print("\t\tCalories: " + calories)
		print("\t\tProtein: " + protein)
		print("\t\tFat: " + fat)
		print("\t\tCarbs: " + carbs)
		print("\t\tFiber: " + fiber)
		print("\t\tSugar: " + sugar)
		print("\t\tIngredients: " + ing)

		time.sleep(randint(2,6))
	
	return

def parseRows(rows):
#Takes in all occurrences of 'tr' and parses appropriately
	helpArr = [None]*6
	###### INDEX KEY:
			# 0 = calRow
			# 1 = proRow
			# 2 = fatRow
			# 3 = carbRow
			# 4 = fibRow
			# 5 = sugRow
	for i in range(len(rows)):
		tempRow = rows[i]
		tempCol = tempRow.findChildren('td') #All columns
		if (tempCol[1].text.strip() == 'Energy'):
			helpArr[0] = i
		elif (tempCol[1].text.strip() == 'Protein'):
			helpArr[1] = i
		elif (tempCol[1].text.strip() == 'Total lipid (fat)'):
			helpArr[2] = i
		elif ( tempCol[1].text.strip() == 'Carbohydrate, by difference'):
			helpArr[3] = i
		elif ( tempCol[1].text.strip() == 'Fiber, total dietary'):
			helpArr[4] = i
		elif ( tempCol[1].text.strip() == 'Sugars, total'):
			helpArr[5] = i
		else:
			continue

	#for x in range(len(helpArr)):
	#	print(helpArr[x])
	return helpArr


def getSoup(urlToTry):
#Exception handler and modularized URL opener
	for attempts in range(10):
		
		pageHTML = requests.get(urlToTry, headers = headers)

		if (pageHTML.status_code < 200) or (pageHTML.status_code > 299):
			print("Connection Error...Sleeping and retrying " + str(10-attempts) + " more times")
			time.sleep(10)
		elif attempts == 9:
			print("Failed after 10 tries on page " + str(pageNum))
			sys.exit(1)
		else:
			pageHTML = pageHTML.text
			break

	pageSoup = soup(pageHTML, "html5lib")

	return pageSoup

#######TODO: ADD FOR LOOP HERE#######
jumpSoup = getSoup(startURL)
startPNum = jumpSoup.find("span",{"class":"currentStep"})

if bool(startPNum):
	startPNum = jumpSoup.find("span",{"class":"currentStep"}).text
else:
	startPNum = None

#loops = endPage - startPNum
#for pagesScanned in range(loops):
fullNextLink = None
firstRun = True
while True:
	
	if firstRun:
		metaData = getLinks(startURL)
	else:
		metaData = getLinks(fullNextLink)
		#Because getLinks() returns 3 values, we must "unpack" it here
	urlList = metaData[0]
	fullNextLink = metaData[1]
	pageNum = metaData[2]

	print("STARTING SCRAPE ON PAGE " + str(pageNum))
	
	z = scrapeLinks(urlList)
	
	if pageNum == endPage:
		print("Finished Successfully")
		break
	
	firstRun = False
	print("Sleeping before starting next page...")
	time.sleep(30)

