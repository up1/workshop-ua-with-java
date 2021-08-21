package main

import (
	"fmt"

	"golang.org/x/net/idna"
)

func main(){
	domain := "ไทยร่วมใจ.com"
	normalized, _ := idna.ToASCII(domain)
	displayed, _ := idna.ToUnicode(normalized)

	fmt.Println("domain: ", domain)
	fmt.Println("normalized: ", normalized)
	fmt.Println("displayed: ", displayed)
}