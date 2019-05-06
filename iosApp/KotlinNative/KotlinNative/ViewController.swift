//
//  ViewController.swift
//  KotlinNative
//
//  Created by Tim Lu on 2019/5/5.
//  Copyright © 2019 Tim Lu. All rights reserved.
//

import UIKit
import shared

class ViewController: UIViewController, MembersView {
    
    let memberList = MemberList()
    
    lazy var presenter: MemberPresenter = {
        MemberPresenter(view: self, repository: AppDelegate.appDeledated.dataRepository())
    }()
    
     var isUpdating = false
    
    @IBOutlet weak var greeting: UILabel!
    @IBOutlet weak var memberTableView: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        greeting.text = Greeting().greeting()
        
        memberTableView.register(UINib(nibName: "MemberCellTableViewCell", bundle: nil), forCellReuseIdentifier: "MemberCell")
        // Do any additional setup after loading the view, typically from a nib.
        memberTableView.delegate = self
    }
    
    override func viewWillAppear(_ animated: Bool) {
        presenter.onCreate()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        presenter.onDestroy()
    }
    
    func onUpdate(members: [Member]) {
        self.memberList.updateMembers(members)
        self.memberTableView.reloadData()
    }

}

extension ViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MemberCell",for: indexPath) as! MemberCellTableViewCell
        let member = self.memberList.members[indexPath.row]
        cell.memberLabel.text = member.login
        cell.memberAvatar.load(url: URL(string: member.avatarUrl)!)
        return  cell
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.memberList.members.count
    }
}

extension ViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 88
    }
}

extension UIImageView {
    func load(url: URL) {
        DispatchQueue.global().async { [weak self] in
            if let data = try? Data(contentsOf: url) {
                if let image = UIImage(data: data) {
                    DispatchQueue.main.async {
                        self?.image = image
                    }
                }
            }
        }
    }
}

